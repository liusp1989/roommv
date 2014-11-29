package com.liusp.roommv.entity.base;

import java.util.Date;

import javax.persistence.Column;

public abstract class AbstractCRUDBaseBO {

	@Column(name = "creator")
	private String creator;

	@Column(name = "createDate")
	private Date createDate;

	@Column(name = "updator")
	private String updator;

	@Column(name = "updateDate")
	private Date updateDate;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
