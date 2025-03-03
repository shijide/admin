package com.knowledge.admin.base.user.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.feilong.core.bean.ConvertUtil;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.admin.base.role.entity.TbRole;
import com.knowledge.admin.base.role.service.ITbRoleService;
import com.knowledge.admin.base.user.entity.TbUser;
import com.knowledge.admin.base.user.service.ITbUserService;
import com.knowledge.admin.base.util.EndecryptUtils;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;

/**
 * <p>
 * 用户账户表  前端控制器
 * </p>
 *
 * @author xj
 * @since 2016-12-20
 */
@Controller
@RequestMapping("/user/")
public class TbUserController extends BaseAdminController{
	@Autowired
	private ITbUserService userService;
	@Autowired
	private ITbRoleService roleService;
	
	@RequestMapping("listUI")
    public String listUI(Map<String,Object> map,Integer page,String name) {
		Page<TbUser> users = userService.selectUserList(new Page<TbUser>(null==page?0:page, 10),name,getUserEntity().getRole().getKey());
		map.put("page", users);
		map.put("name",name==null?"":name);
		return "user/list";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		List<TbRole> list = roleService.selectList(Condition.create().ne("r_key", "administrator"));
		map.put("roles", list);
		return "user/form";
    }
	
	@RequestMapping(value="checkAccount",method=RequestMethod.GET)
	@ResponseBody
    public boolean checkAccount(@RequestParam(required=true) String accountName) {
		List<TbUser> user = userService.selectByMap(ConvertUtil.toMap("u_account_name",(Object)accountName));
		if(!Validator.isNullOrEmpty(user)){
			return false;
		}
		return true;
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Map<String,Object> map,TbUser user){
		if(user.getId()==null){
			// 加密用户输入的密码，得到密码和加密盐，保存到数据库
			TbUser userEntity = EndecryptUtils.md5Password(user.getAccountName(), user.getPassword(), 2);
			//设置添加用户的密码和加密盐
			user.setPassword(userEntity.getPassword());
			user.setCredentialsSalt(userEntity.getCredentialsSalt());
			//设置创建者姓名
			user.setCreatorName(getUserEntity().getAccountName());
			user.setCreateTime(new Date(System.currentTimeMillis()));
			user.setUpdateTime(new Date(System.currentTimeMillis()));
			//设置锁定状态：未锁定；删除状态：未删除
			user.setLocked(0);
			user.setDeleteStatus(0);
			boolean result = userService.insertAll(user);
			if(!result)
			{
				return error();
			}
		}else{
			userService.updateAll(user);
		}
	
		return success();
	}
	
	@RequestMapping(value="{userId}/delete",method=RequestMethod.DELETE)
	@ResponseBody
    public Result delete(@PathVariable(required=true) Integer userId) {
		if (userId.equals(getUserEntity().getId()) || userId == 1) {
			return fail(EnumResult.ERROR_ILLEGAL_OPERATION);
		}
		if(!userService.delUser(userId)){
			return error();
		}
		return success();
    }	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="{userId}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer userId) {	
		TbUser user = userService.selectUserRole(ConvertUtil.toMap("userId",(Object)userId));
		List<TbRole> list = roleService.selectList(Condition.create().ne("r_key", "administrator"));
		map.put("roles", list);
		map.put("user", user==null?new TbUser():user);
		return "user/edit";
    }	
	
	@RequestMapping(value="{userId}/toRestPassword",method=RequestMethod.GET)
    public String restPassword(Map<String,Object> map,@PathVariable(required=true) Integer userId) {	
		TbUser user = userService.selectById(userId);
		map.put("user", user);
		return "user/rest";
    }	
	
	@RequestMapping(value = "restPassword", method = RequestMethod.POST)
	@ResponseBody
	public Result restPassword(Map<String,Object> map,TbUser user){
		TbUser userEntity = userService.selectById(user.getId());
		//TODO 重置后需发新密码至用户邮箱
		TbUser userFlag = EndecryptUtils.md5Password(user.getAccountName(), user.getPassword(), 2);
		//设置添加用户的密码和加密盐
		userEntity.setPassword(userFlag.getPassword());
		userEntity.setCredentialsSalt(userFlag.getCredentialsSalt());
		userEntity.setUpdateTime(new Date(System.currentTimeMillis()));
		if(!userService.updateById(userEntity)){
			return error();
		}
		return success();
	}
}
