package com.liusp.roommv.visitor;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.htmlparser.util.ParserException;

import com.liusp.roommv.entity.webPage.WebPage;

public interface IWebPageVisitor {
	public void visit(WebPage webPage) throws SQLException,
			UnsupportedEncodingException;

	public String parseBody() throws ParserException;

	public String parseTitle() throws ParserException;
}
