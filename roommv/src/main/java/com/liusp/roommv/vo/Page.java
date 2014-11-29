package com.liusp.roommv.vo;

public class Page {
	private Integer start;
	private Integer end;
	private Integer count = 10;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
		this.caculate();
	}

	public Integer getEnd() {
		return end;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
		this.caculate();
	}

	private void caculate() {
		this.end = this.start + this.count;
	}
}
