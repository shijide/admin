package com.knowledge.common.business.periodicaltips.entity;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 刊首寄语
 * </p>
 *
 * @author aron
 * @since 2018-12-08
 */
@TableName("tb_periodical_tips")
public class TbPeriodicalTips extends Model<TbPeriodicalTips> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 标题
     */
	private String title;
    /**
     * 姓名
     */
	private String name;
    /**
     * 职位
     */
	@TableField("job_position")
	private String jobPosition;
    /**
     * 创建人
     */
	@TableField("create_name")
	private String createName;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 状态（0=下架 1=上架）
     */
	private Integer status;
    /**
     * 刊首寄语
     */
	private String content;
    /**
     * 寄语人头像url
     */
	@TableField("head_portrait_url")
	private String headPortraitUrl;
	
	@TableField("is_home")
	private Integer isHome;
	
	//标签
	private String label;
	
	//权重
	private Integer weight;
	
	//分类 1=成长寄语   2=校长贈言
	private Integer type;
	
    /**
     * 创建人
     */
	@TableField("update_name")
	private String updateName;
    /**
     * 创建时间
     */
	@TableField("update_time")
	private Date updateTime;
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getIsHome() {
		return isHome;
	}

	public void setIsHome(Integer isHome) {
		this.isHome = isHome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}

	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "TbPeriodicalTips [id=" + id + ", title=" + title + ", name=" + name + ", jobPosition=" + jobPosition
				+ ", createName=" + createName + ", createTime=" + createTime + ", status=" + status + ", content="
				+ content + ", headPortraitUrl=" + headPortraitUrl + ", isHome=" + isHome + ", label=" + label
				+ ", weight=" + weight + ", type=" + type + ", updateName=" + updateName + ", updateTime=" + updateTime
				+ "]";
	}

	
	
	



	
	


	
	
	
}
