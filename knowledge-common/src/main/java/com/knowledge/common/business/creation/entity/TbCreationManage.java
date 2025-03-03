package com.knowledge.common.business.creation.entity;


import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 创作天地管理
 * </p>
 *
 * @author xiong
 * @since 2018-12-14
 */
@TableName("tb_creation_manage")
public class TbCreationManage extends Model<TbCreationManage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 作品名称
     */
	private String name;
    /**
     * 作品类型（0=书法 1=绘画 2=摄影 3=多媒体 4=作文 5=其他）
     */
	private Integer type;
    /**
     * 内容
     */
	private String content;
    /**
     * 学生姓名
     */
	@TableField(value="student_name",strategy = FieldStrategy.IGNORED)
	private String studentName;
    /**
     * 学校
     */
	@TableField(strategy = FieldStrategy.IGNORED)
	private String school;
    /**
     * 首页推荐（1=显示  2=不显示）
     */
	@TableField("is_home")
	private Integer isHome;
    /**
     * 创建人
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;
    /**
     * 状态（1=上架 2=下架）
     */
	private Integer status;
    /**
     * 封面图url
     */
	@TableField(value="cover_url",strategy = FieldStrategy.IGNORED)
	private String coverUrl;
    /**
     * 权重
     */
	private Integer weight;
	@TableField("update_user")
	private String updateUser;
	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	private Date updateTime;
    /**
     * 作品推荐 1是2否  （最大3个）
     */
	@TableField("recommend_works")
	private Integer recommendWorks;
    /**
     * 多媒体url
     */
	@TableField(value="vedio_url",strategy = FieldStrategy.IGNORED)
	private String vedioUrl;


	public Integer getId() {
		return id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Integer getIsHome() {
		return isHome;
	}

	public void setIsHome(Integer isHome) {
		this.isHome = isHome;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRecommendWorks() {
		return recommendWorks;
	}

	public void setRecommendWorks(Integer recommendWorks) {
		this.recommendWorks = recommendWorks;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbCreationManage{" +
			", id=" + id +
			", name=" + name +
			", type=" + type +
			", content=" + content +
			", studentName=" + studentName +
			", school=" + school +
			", isHome=" + isHome +
			", createUser=" + createUser +
			", createTime=" + createTime +
			", status=" + status +
			", coverUrl=" + coverUrl +
			", weight=" + weight +
			", updateUser=" + updateUser +
			", updateTime=" + updateTime +
			", recommendWorks=" + recommendWorks +
			", vedioUrl=" + vedioUrl +
			"}";
	}
}
