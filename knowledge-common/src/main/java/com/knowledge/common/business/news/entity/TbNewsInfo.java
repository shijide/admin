package com.knowledge.common.business.news.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import com.knowledge.common.business.message.entity.TbMessageManage;

/**
 * <p>
 * 资讯信息详情
 * </p>
 *
 * @author francis
 * @since 2018-11-09
 */
@TableName("tb_news_info")
public class TbNewsInfo extends Model<TbNewsInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 类别ID
     */
	@TableField("category_id")
	private Integer categoryId;
    /**
     * 标题名称
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 来源/作者
     */
	@TableField(strategy = FieldStrategy.IGNORED)
	private String author;
    /**
     * 评论总数
     */
	@TableField("comment_total")
	private Integer commentTotal;
    /**
     * 浏览量
     */
	@TableField("browse_total")
	private Integer browseTotal;
    /**
     * 是否置顶(1=置顶，2=不置顶)
     */
	@TableField("set_top")
	private Integer setTop;
    /**
     * 置顶时间
     */
	@TableField(value = "top_time",strategy = FieldStrategy.IGNORED)
	private Date topTime;
    /**
     * 状态(1=上架,2=下架)
     */
	private Integer status;
	
	/**
	 * 是否首页推荐 1是2否
	 */
	private Integer isHome;
	
    /**
     * 评论是否开放(1=开放，2=不开放)
     */
	@TableField("open_comment")
	private Integer openComment;
    /**
     * pdf链接
     */
	@TableField(value="pdf_url",strategy = FieldStrategy.IGNORED)
	private String pdfUrl;
    /**
     * 附件链接
     */
	@TableField(value="enclosure_url",strategy = FieldStrategy.IGNORED)
	private String enclosureUrl;
	
	/**
	 * 视频链接
	 */
	@TableField(value="video_url",strategy = FieldStrategy.IGNORED)
	private String videoUrl;
	
	/**
	 * 权重
	 */
	@TableField("weight")
	private Integer weight;
	
	/**
	 * 封面图
	 */
	@TableField(value="cover_url",strategy = FieldStrategy.IGNORED)
	private String coverUrl;
	
	
    /**
     * 创建时间
     */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	private Date createTime;
    /**
     * 创建人
     */
	@TableField(value = "create_user")
	private String createUser;
    /**
     * 修改时间
     */
	@TableField(value = "update_time",fill = FieldFill.UPDATE)
	private Date updateTime;
    /**
     * 修改人
     */
	@TableField(value = "update_user")
	private String updateUser;

	@TableField(exist = false)
	private String categoryName;
	
	
	@TableField(exist = false)
	private List<TbMessageManage> messageList;
	
	

	public List<TbMessageManage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<TbMessageManage> messageList) {
		this.messageList = messageList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getCommentTotal() {
		return commentTotal;
	}

	public void setCommentTotal(Integer commentTotal) {
		this.commentTotal = commentTotal;
	}

	public Integer getBrowseTotal() {
		return browseTotal;
	}

	public void setBrowseTotal(Integer browseTotal) {
		this.browseTotal = browseTotal;
	}

	public Integer getSetTop() {
		return setTop;
	}

	public void setSetTop(Integer setTop) {
		this.setTop = setTop;
	}

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOpenComment() {
		return openComment;
	}

	public void setOpenComment(Integer openComment) {
		this.openComment = openComment;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public String getEnclosureUrl() {
		return enclosureUrl;
	}

	public void setEnclosureUrl(String enclosureUrl) {
		this.enclosureUrl = enclosureUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public Integer getIsHome() {
		return isHome;
	}

	public void setIsHome(Integer isHome) {
		this.isHome = isHome;
	}

	@Override
	public String toString() {
		return "TbNewsInfo [id=" + id + ", categoryId=" + categoryId + ", title=" + title + ", content=" + content
				+ ", author=" + author + ", commentTotal=" + commentTotal + ", browseTotal=" + browseTotal + ", setTop="
				+ setTop + ", topTime=" + topTime + ", status=" + status + ", openComment=" + openComment + ", pdfUrl="
				+ pdfUrl + ", enclosureUrl=" + enclosureUrl + ", videoUrl=" + videoUrl + ", weight=" + weight
				+ ", coverUrl=" + coverUrl + ", createTime=" + createTime + ", createUser=" + createUser
				+ ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", categoryName=" + categoryName + "]";
	}
	
}
