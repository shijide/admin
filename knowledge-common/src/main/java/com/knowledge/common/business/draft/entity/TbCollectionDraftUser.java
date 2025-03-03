package com.knowledge.common.business.draft.entity;

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
 * 投稿管理
 * </p>
 *
 * @author aron
 * @since 2018-12-03
 */
@TableName("tb_collection_draft_user")
public class TbCollectionDraftUser extends Model<TbCollectionDraftUser> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableField("draft_id")
	private Integer draftId;
	/**
	 * 作品名称
	 */
	private String name;
	/**
	 * 作品类型（0=书法 1=绘画 2=摄影 3=多媒体 4=作文 5=其他）
	 */
	private Integer type;
	/**
	 * 学生姓名（学生1，学生2，学生3）
	 */
	@TableField("student_name")
	private String studentName;
	/**
	 * 所属学校名称
	 */
	@TableField("school_name")
	private String schoolName;
	/**
	 * 年班
	 */
	@TableField("class_name")
	private String className;
	/**
	 * 創建人
	 */
	@TableField("create_user")
	private String createUser;
	/**
	 * Email
	 */
	private String email;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * WhatsApp
	 */
	private String whatsapp;
	/**
	 * 指导老师
	 */
	private String teacher;
	/**
	 * 作品说明
	 */
	private String explain;
	/**
	 * 投稿时间
	 */
	@TableField(value = "draft_time", fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 状态（0=待审核 1=通过 2=不通过）
	 */
	private Integer status;
	/**
	 * 附件url
	 */
	@TableField("enclosure_url")
	private String enclosureUrl;

	@TableField("enclosure_name")
	private String enclosureName;
	
	@TableField("video_url")
	private String videoUrl;
	
	private String reviewer;
	
	@TableField("review_time")
	private Date reviewTime;
	
	@TableField("video_type")
	private Integer videoType;
	
	public Integer getVideoType() {
		return videoType;
	}

	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}

	public Integer getId() {
		return id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getDraftId() {
		return draftId;
	}

	public void setDraftId(Integer draftId) {
		this.draftId = draftId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEnclosureUrl() {
		return enclosureUrl;
	}

	public void setEnclosureUrl(String enclosureUrl) {
		this.enclosureUrl = enclosureUrl;
	}

	public String getEnclosureName() {
		return enclosureName;
	}

	public void setEnclosureName(String enclosureName) {
		this.enclosureName = enclosureName;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbCollectionDraftUser [id=" + id + ", draftId=" + draftId + ", name=" + name + ", type=" + type
				+ ", studentName=" + studentName + ", schoolName=" + schoolName + ", className=" + className
				+ ", createUser=" + createUser + ", email=" + email + ", phone=" + phone + ", whatsapp=" + whatsapp
				+ ", teacher=" + teacher + ", explain=" + explain + ", createTime=" + createTime + ", status=" + status
				+ ", enclosureUrl=" + enclosureUrl + ", enclosureName=" + enclosureName + ", videoUrl=" + videoUrl
				+ ", reviewer=" + reviewer + ", reviewTime=" + reviewTime + "]";
	}

	
}
