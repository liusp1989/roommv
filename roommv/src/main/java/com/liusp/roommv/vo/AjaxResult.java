package com.liusp.roommv.vo;

public class AjaxResult {
private int resultCode;
private String msg;
private Object value;
public int getResultCode() {
	return resultCode;
}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode.getCode();
		this.msg = resultCode.getName();
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public Object getValue() {
	return value;
}
public void setValue(Object value) {
	this.value = value;
}

	public static enum ResultCode {
		SUCCESS(0, "操作成功"), FAILED(1, "操作失败");
	private int code;
	private String name;

		ResultCode(int code, String name) {
		this.code =code;
		this.name =name;
	}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
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
