package com.knowledge.api.business.member;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.Condition;
import com.feilong.core.Validator;
import com.knowledge.api.base.config.jwt.annotation.UserLoginToken;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.base.util.MD5Util;
import com.knowledge.common.business.member.entity.TbBindingUser;
import com.knowledge.common.business.member.entity.TbMember;
import com.knowledge.common.business.member.service.ITbBindingUserService;
import com.knowledge.common.business.member.service.ITbMemberService;
import com.knowledge.common.business.notice.entity.TbNotice;
import com.knowledge.common.business.notice.service.ITbNoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户信息管理 前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-11-30
 */
@RestController
@Api(value = "用户Controller", tags = { "用户操作接口" })
public class TbMemberController extends BaseController {

	@Autowired
	private ITbMemberService memberService;
	
	@Autowired
	private ITbNoticeService noticeService;
	

	
	@Autowired
	private ITbBindingUserService iBindingUserService;

	@UserLoginToken
	@PostMapping(value = "/member/updateInfo")
	@ApiOperation(value = "修改个人信息", notes = "修改个人信息")
	public Result delete(@RequestBody TbMember member) {
		if (!memberService.updateById(member)) {
			return error();
		}
		return success();
	}

	@UserLoginToken
	@GetMapping(value = "/member/getMemberInfo")
	@ApiOperation(value = "获取个人信息", notes = "获取个人信息")
	public Result select() {
		TbMember meber =getMemberInfo();
		List<TbBindingUser> bindUsers=	iBindingUserService.selectList(Condition.create().eq("user_id", meber.getId()));
		meber.setBindUsers(bindUsers);
		return json(meber);
	}
	
	@UserLoginToken
	@GetMapping(value = "/member/relieveBind")
	@ApiOperation(value = "解除绑定", notes = "解除绑定")
	public Result relieveBind(@RequestParam Integer id) {
		return iBindingUserService.deleteById(id)?success():error();
		 
	}
	
	
	@UserLoginToken
	@GetMapping(value = "/member/getNoticeList")
	@ApiOperation(value = "我的信息列表", notes = "获取信息列表")
	public Result selectMessage() {
		return json(noticeService.selectList(Condition.create().eq("to_mem_id", getuserId())));
	}
	
	
	
	@UserLoginToken
	@GetMapping(value = "/member/getNoticeInfo")
	@ApiOperation(value = "信息詳情", notes = "获取信息詳情")
	public Result selectNotice(@RequestParam Integer id) {
		TbNotice notice=noticeService.selectById(id);
		if(Validator.isNotNullOrEmpty(notice)) {
			notice.setStatus(1);
			noticeService.updateById(notice);
			return json(notice);
		}
		return error();
	}

	// @UserLoginToken
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/member/forgetPassword")
	@ApiOperation(value = "忘记密码，发送邮件", notes = "忘记密码，发送邮件")
	public Result forgetPassword(@RequestParam String email) {
		String hostName = request.getHeader("Referer");
		TbMember tbmember = memberService.selectOne(Condition.create().eq("email", email));
		if (Validator.isNullOrEmpty(tbmember)) {
			return fail(EnumResult.ERROR_EMAIL_NOT_REGISTER);
		}
		tbmember.setHostName(hostName);
		memberService.forgetPass(tbmember);
		return success();
	}

	@PostMapping("/member/checkPass")
	@ApiOperation(value = "修改密码验证链接", notes = "修改密码验证链接")
	public Result active(@RequestParam String code) {
		if (Validator.isNullOrEmpty(code)) {
			return fail(EnumResult.ERROR_PARAM);
		}
		TbMember member = memberService.selectOne(Condition.create().eq("active_code", code));
		if (Validator.isNullOrEmpty(member)) {
			return fail(EnumResult.ERROR_URL_INVALID);
		}
		if (!memberService.checkPassTimeOut(member)) {
			return fail(EnumResult.ERROR_URL_TIMEOUT);
		}
		return success();

	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/member/updatePassword")
	@ApiOperation(value = "修改密码", notes = "修改密码 type 1忘记密码  2原密码修改密码")
	public Result updatePassword(@RequestBody TbMember member) {
		if (Validator.isNotNullOrEmpty(member.getType())) {
			if (member.getType().intValue() == 1) {
				TbMember tbmember = memberService.selectOne(Condition.create().eq("active_code", member.getCode()));
				if (Validator.isNullOrEmpty(tbmember)) {
					return error();
				}
				tbmember.setPassword(MD5Util.md5(member.getPassword()));
				//tbmember.setActiveTime(new Date());
				tbmember.setActiveCode(null);
				if (!tbmember.updateById()) {
					return error();
				}
				return success();
			}
			if (member.getType().intValue() == 2) {
				TbMember tbmember = memberService.selectById(member.getId());
				if (Validator.isNullOrEmpty(tbmember)) {
					return error();
				}
				if (!MD5Util.md5(member.getOldPassword()).equals(tbmember.getPassword())) {
					return fail(EnumResult.ERROR_USER_OLD_PASS_DIFFERENT);
				}
				tbmember.setPassword(MD5Util.md5(member.getPassword()));
				if (!tbmember.updateById()) {
					return error();
				}
				return success();
			}

		}

		return fail(EnumResult.ERROR_PARAM);
	}
	
	@UserLoginToken
	@PostMapping(value = "/member/updemail")
	@ApiOperation(value = "订阅邮箱", notes = "订阅邮箱")
	public Result delete(@RequestParam String email) {
		TbMember member=getMemberInfo();
		member.setSubEmail(email);
		member.setSubTime(new Date());
		if (!member.updateById()) {
			return error();
		}
		return success();
	}
	
	@GetMapping(value = "/member/checkBind")
	@ApiOperation(value = "第三方登录验证是否绑定系统用户", notes = "第三方登录验证是否绑定系统用户")
	public Result checkBind(@RequestParam String thirdId) {
		TbBindingUser user=iBindingUserService.selectOne(Condition.create().eq("platform_id", thirdId));
		if (Validator.isNullOrEmpty(user)) {
			return fail(EnumResult.ERROR_NOT_BINDING);
		}
		TbMember member=memberService.selectById(user.getUserId());
		if(Validator.isNullOrEmpty(member)) {
			return fail(EnumResult.DEFIN_ACCOUNT);
		}
		if(member.getActiveState()!=1) {
		return fail(EnumResult.ERROR_ACTIVE_NOT);
		}
		return json(memberService.login(member));
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/member/bindUser")
	@ApiOperation(value = "绑定系统用户", notes = "绑定系统用户")
	public Result checkBind(@RequestBody TbBindingUser bindUser) {
		TbBindingUser uses=iBindingUserService.selectOne(Condition.create().eq("email", bindUser.getEmail()).eq("platform", bindUser.getPlatform()));
		if(Validator.isNotNullOrEmpty(uses)) {
			return fail(EnumResult.ERROR_USE_BINDING);
		}
		TbBindingUser user=iBindingUserService.selectOne(Condition.create().eq("platform_id", bindUser.getPlatformId()));
		if(Validator.isNotNullOrEmpty(user)) {
			return fail(EnumResult.ERROR_USE_THIRD_BINDING);
		}
		TbMember member=memberService.selectOne(Condition.create().eq("email", bindUser.getEmail()));
		
		if (Validator.isNullOrEmpty(member)) {
			return fail(EnumResult.DEFIN_ACCOUNT);
		}
		String pass=MD5Util.md5(bindUser.getPassword());
		if(!(member.getPassword()).equals(pass)) {
			return fail(EnumResult.ERROR_PASSWORD);
		}
		
		bindUser.setUserId(member.getId());
		bindUser.insert();
		return json(memberService.login(member));
	}

}
