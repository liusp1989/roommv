package com.liusp.roommvserver.common;

public class Constants {
	public static final String REDIS_ID_TABLE = "key";
	public static final String REDIS_KEY_SEPARATOR = ":";
	public static enum SynBizType {
		VISITINFO("visitInfo");
		private String bizType;

		SynBizType(String bizType) {
			this.bizType = bizType;
		}

		public String getBizType() {
			return bizType;
		}

	}
}
