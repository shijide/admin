package com.knowledge.common.business.activity.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 活动报名管理
 * </p>
 *
 * @author 

 * @since 2018-11-14
 */
@TableName("tb_activity_enroll_manage")
public class TbActivityEnrollManage extends Model<TbActivityEnrollManage> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 活动ID
	 */
	@TableField("activity_id")
	private Integer activityId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别(0=男 1=女)
	 */
	private Integer gender;
	/**
	 * 所属学校
	 */
	private String school;
	/**
	 * 年班
	 */
	@TableField("annual_class")
	private String annualClass;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 报名时间(相当于创建时间)
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 付款凭证url
	 */
	@TableField("pay_voucher_url")
	private String payVoucherUrl;
	/**
	 * 审核人
	 */
	@TableField("examine_name")
	private String examineName;
	/**
	 * 审核时间
	 */
	@TableField("examine_time")
	private Date examineTime;

	
    /**
     * 電話
     */
	private String phone;
	
    /**
     * 状态（0=待确认 1=已确认 2=拒绝）
     */
	private Integer status;
	
	/**
	 * 修改時間
	 */
	private Date updateTime;
	
	
	/**
	 * 活動名字
	 */
	@TableField(exist=false)
	private String activityName;
	
	
	/**
	 * 活動收費狀態
	 * 1=收費活動  2=免費活動
	 * @return
	 */
	@TableField(exist=false)
	private Integer isCost;

    /**
     * 报名开始时间
     */
	@TableField(exist=false)
	private Date enrollStartTime;
    /**
     * 报名结束时间
     */
	@TableField(exist=false)
	private Date enrollEndTime;
    /**
     * 活动开始时间
     */
	@TableField(exist=false)
	private Date activityStartTime;
    /**
     * 活动结束时间
     */
	@TableField(exist=false)
	private Date activityEndTime;
	

	@TableField(value = "create_user", fill = FieldFill.INSERT)
	private String createUser;


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAnnualClass() {
		return annualClass;
	}

	public void setAnnualClass(String annualClass) {
		this.annualClass = annualClass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPayVoucherUrl() {
		return payVoucherUrl;
	}

	public void setPayVoucherUrl(String payVoucherUrl) {
		this.payVoucherUrl = payVoucherUrl;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}

	public Date getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getIsCost() {
		return isCost;
	}

	public void setIsCost(Integer isCost) {
		this.isCost = isCost;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getEnrollStartTime() {
		return enrollStartTime;
	}

	public void setEnrollStartTime(Date enrollStartTime) {
		this.enrollStartTime = enrollStartTime;
	}

	public Date getEnrollEndTime() {
		return enrollEndTime;
	}

	public void setEnrollEndTime(Date enrollEndTime) {
		this.enrollEndTime = enrollEndTime;
	}

	public Date getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(Date activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	@Override
	public String toString() {
		return "TbActivityEnrollManage [id=" + id + ", activityId=" + activityId + ", name=" + name + ", gender="
				+ gender + ", school=" + school + ", annualClass=" + annualClass + ", email=" + email + ", createTime="
				+ createTime + ", payVoucherUrl=" + payVoucherUrl + ", examineName=" + examineName + ", examineTime="
				+ examineTime + ", phone=" + phone + ", status=" + status + ", updateTime=" + updateTime
				+ ", activityName=" + activityName + ", isCost=" + isCost + ", enrollStartTime=" + enrollStartTime
				+ ", enrollEndTime=" + enrollEndTime + ", activityStartTime=" + activityStartTime + ", activityEndTime="
				+ activityEndTime + ", createUser=" + createUser + "]";
	}


	
	


	
	
}
