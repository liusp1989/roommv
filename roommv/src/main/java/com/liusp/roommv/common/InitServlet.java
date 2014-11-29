package com.liusp.roommv.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InitServlet() {
		super();
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// String webAppPath = config.getServletContext().getRealPath(".");
		// System.setProperty(RoommvConstant.WEB_APP_PATH_KEY,
		// webAppPath.replace("\\", "/"));
	}
}
