package com.liusp.roommv.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.liusp.roommv.socket.SocketClient;

public class InitServlet extends HttpServlet {
	public static final Logger logger = Logger.getLogger(InitServlet.class);
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
		try {
			SocketClient.createClientSocket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("启动netty客户端失败", e);
		}
	}
}
