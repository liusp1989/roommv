package com.liusp.roommv.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.liusp.roommv.socket.SocketClient;

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
			SocketClient.createClientSocket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
