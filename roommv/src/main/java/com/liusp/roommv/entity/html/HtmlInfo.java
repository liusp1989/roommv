package com.liusp.roommv.entity.html;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jackyliu
 * 
 */
public class HtmlInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String imageUrl;
	private String htmlId;
	private String content;
	private String ipInfo;
	private Integer viewTimes;
	private Integer shareTimes;
	private Integer goodTimes;
	private Integer notGoodTimes;
	private String handleStatus;
	private String auditStatus;
	private String remark;
	private Date createDate;
	private String creator;
	private Date updateDate;
	private String updator;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIpInfo() {
		return ipInfo;
	}

	public void setIpInfo(String ipInfo) {
		this.ipInfo = ipInfo;
	}

	public Integer getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	public Integer getShareTimes() {
		return shareTimes;
	}

	public void setShareTimes(Integer shareTimes) {
		this.shareTimes = shareTimes;
	}

	public Integer getGoodTimes() {
		return goodTimes;
	}

	public void setGoodTimes(Integer goodTimes) {
		this.goodTimes = goodTimes;
	}

	public Integer getNotGoodTimes() {
		return notGoodTimes;
	}

	public void setNotGoodTimes(Integer notGoodTimes) {
		this.notGoodTimes = notGoodTimes;
	}

	public String getHtmlId() {
		return htmlId;
	}

	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public static enum HandleStatus {
		EDIT_ING("ing", "正在编辑"), EDIT_SAVE("save", "已保存"), EDIT_SUBMIT(
				"submit", "已投稿");
		private String code;
		private String name;

		HandleStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
