package com.liusp.roommv.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class LocalCommonsMultipartResolver extends CommonsMultipartResolver {
	public boolean isMultipart(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		if (url.contains(RoommvConstant.NO_MULTI_VALIDATE)) {
			return false;
		}
		return (request != null && ServletFileUpload
				.isMultipartContent(request));
	}
}
