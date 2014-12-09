package com.liusp.roommv.entity.html;



public class HtmlTemplate {

	private String id;
	private String beforeHeader;
	private String afterHeader;
	private String beforeContent;
	private String afterContent;
	private String beforeFooter;
	private String afterFooter;
	private Boolean useStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBeforeContent() {
		return beforeContent;
	}

	public void setBeforeContent(String beforeContent) {
		this.beforeContent = beforeContent;
	}

	public String getAfterContent() {
		return afterContent;
	}

	public void setAfterContent(String afterContent) {
		this.afterContent = afterContent;
	}

	public String getBeforeFooter() {
		return beforeFooter;
	}

	public void setBeforeFooter(String beforeFooter) {
		this.beforeFooter = beforeFooter;
	}

	public String getAfterFooter() {
		return afterFooter;
	}

	public void setAfterFooter(String afterFooter) {
		this.afterFooter = afterFooter;
	}

	public String getBeforeHeader() {
		return beforeHeader;
	}

	public void setBeforeHeader(String beforeHeader) {
		this.beforeHeader = beforeHeader;
	}

	public String getAfterHeader() {
		return afterHeader;
	}

	public void setAfterHeader(String afterHeader) {
		this.afterHeader = afterHeader;
	}

	public Boolean getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Boolean useStatus) {
		this.useStatus = useStatus;
	}

}
