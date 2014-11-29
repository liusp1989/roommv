package com.liusp.roommv.entity.html;

import javax.persistence.Column;

import com.liusp.roommv.entity.base.AbstractCRUDBaseBO;

public class HtmlInfo extends AbstractCRUDBaseBO {
	@Column(name = "id")
	private String id;

	@Column(name = "imageUrl")
	private String imageUrl;

	@Column(name = "ipInfo")
	private String ipInfo;

	@Column(name = "viewTimes")
	private Integer viewTimes;

	@Column(name = "shareTimes")
	private Integer shareTimes;

	@Column(name = "goodTimes")
	private Integer goodTimes;

	@Column(name = "notGoodTimes")
	private Integer notGoodTimes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

}
