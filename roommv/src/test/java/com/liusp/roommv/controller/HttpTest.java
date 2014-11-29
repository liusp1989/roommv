package com.liusp.roommv.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class HttpTest {

	public static void main(String[] args) throws IOException, SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		// HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// CloseableHttpClient client = httpClientBuilder.build();
		// HttpGet httpget = new HttpGet(
		// "http://chs.romantic214.com/yeguangmeiguihua/");
		// // httpget.addHeader(new BasicHeader("", ""));
		// // httpget.addHeader("", "");
		// CloseableHttpResponse httpReponse = client.execute(httpget);
		// try {
		// // 获取状态行
		// System.out.println(httpReponse.getStatusLine());
		// HttpEntity entity = httpReponse.getEntity();
		// // 返回内容
		// System.out.println(EntityUtils.toString(entity));
		// } finally {
		// httpReponse.close();
		// }
		Connection conn = null;
		String sql;
		try {
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		String url = "jdbc:mysql://localhost:3306/roommv?useUnicode=true&characterEncoding=utf8";
			// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
			// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			// or:
			// com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			// or：
			// new com.mysql.jdbc.Driver();

			System.out.println("成功加载MySQL驱动程序");
			// 一个Connection代表一个数据库连接
		conn = DriverManager.getConnection(url, "root", "liusp");
			Statement statement = (Statement) conn
					.createStatement();
			ResultSet set = statement
					.executeQuery("select content from webpage");
			StringBuilder sb = new StringBuilder();
			String str = null;
			while (set.next()) {
				java.sql.Blob blob = set.getBlob("content");
				if (blob != null) {
				System.out.println(new String(blob.getBytes(1,
						(int) blob.length()), "UTF-8"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
}
