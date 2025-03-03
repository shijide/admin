package com.knowledge.api.business.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.feilong.core.bean.ConvertUtil;
import com.knowledge.api.base.config.jwt.annotation.UserLoginToken;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Pager;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.activity.bean.ActivityAndDraft;
import com.knowledge.common.business.activity.entity.TbActivityEnrollManage;
import com.knowledge.common.business.activity.entity.TbActivityManage;
import com.knowledge.common.business.activity.entity.TbPastActivityManage;
import com.knowledge.common.business.activity.service.ITbActivityEnrollManageService;
import com.knowledge.common.business.activity.service.ITbActivityManageService;
import com.knowledge.common.business.activity.service.ITbPastActivityManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/activity/")
@Api(tags = { "活动中心" })
public class ActivityController extends BaseController {
	@Autowired
	private ITbActivityManageService activityManageService;

	@Autowired
	private ITbActivityEnrollManageService activityEnrollManageService;

	@Autowired
	private ITbPastActivityManageService pastActivityManageService;

	@SuppressWarnings("unchecked")
	@GetMapping("getActivityEnrolling")
	@ApiOperation("查询报名中的活动")
	public Result getActivityEnrolling() {
		List<TbActivityManage> list = activityManageService.selectList(Condition.create().eq("status", 1));
		return json(list);
	}

	@GetMapping("getActivityEnrollDetail")
	@ApiOperation(value = "查询活动详情")
	public Result getActivityEnrollDetail(@RequestParam Integer id) {
		TbActivityManage tbActivityManage = activityManageService.selectById(id);
		return json(tbActivityManage);
	}

	@SuppressWarnings("unchecked")
	@PostMapping("getPastActivity")
	@ApiOperation("查询活动回顾")
	public Result getPastActivity(@RequestBody Pager<TbPastActivityManage> pager) {
		Page<TbPastActivityManage> page = pastActivityManageService.selectPage(pager.getPagePlus(),
				Condition.create().eq("status", 1).orderBy("id", false));
		return json(page);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("getPastActivityDetail")
	@ApiOperation("查询活动回顾详情")
	public Result getPastActivityDetail(@RequestParam Integer id) {
		Map<String, Object> map = new HashMap<>();
		TbPastActivityManage pastActivityManage = pastActivityManageService.selectById(id);
		map.put("pastActivityManage", pastActivityManage);
		if (Validator.isNotNullOrEmpty(pastActivityManage)
				&& Validator.isNotNullOrEmpty(pastActivityManage.getRelation())) {
			String relation = pastActivityManage.getRelation();
			if (StringUtils.contains(relation, ",")) {
				String[] split = relation.split(",");
				Integer[] ids = ConvertUtil.toIntegers(split);
				List<TbPastActivityManage> other = new ArrayList<>();
				for (Integer integer : ids) {
					TbPastActivityManage past = pastActivityManageService
							.selectOne(Condition.create().eq("id", integer).eq("status", 1));
					other.add(past);
				}
				map.put("others", other);
			} else {
				List<TbPastActivityManage> list = pastActivityManageService
						.selectList(Condition.create().eq("id", Integer.parseInt(relation)).eq("status", 1));
				map.put("others", list);
			}
		}
		return json(map);
	}

	@PostMapping("addActivityEnroll")
	@ApiOperation(value = "报名活动", notes = "gender(0=男 1=女) " + "传入参数{\r\n" + "  \"activityId\": 0,\r\n"
			+ "  \"annualClass\": \"string\",\r\n" + "  \"email\": \"string\",\r\n" + "  \"gender\": 0,\r\n"
			+ "  \"name\": \"string\",\r\n" + "  \"payVoucherUrl\": \"string\",\r\n" + "  \"phone\": \"string\",\r\n"
			+ "  \"school\": \"string\"\r\n" + "}")
	@UserLoginToken
	public Result addActivityEnroll(@RequestBody TbActivityEnrollManage activityEnrollManage) {
		activityEnrollManage.setCreateUser(getMemberInfo().getNickname());
		activityEnrollManageService.insert(activityEnrollManage);
		return success();
	}

	@PostMapping("getActivityAndDraft")
	@ApiOperation(value = "获取当前用户报名的活动和投稿", notes = "gender(0=男 1=女)，type 1是活动，2是投稿  status （0=待确认 1=已确认 2=拒绝）")
	@UserLoginToken
	public Result getActivityAndDraft(@RequestBody Pager<ActivityAndDraft> pager) {
		return json(activityEnrollManageService.selectUserActivityAndDraft(pager, getMemberInfo().getNickname()));
	}

}
