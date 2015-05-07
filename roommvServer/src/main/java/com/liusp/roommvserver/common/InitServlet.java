package com.liusp.roommvserver.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.liusp.roommvserver.socket.SocketServer;

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
		try {
			SocketServer.createServerSocket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
