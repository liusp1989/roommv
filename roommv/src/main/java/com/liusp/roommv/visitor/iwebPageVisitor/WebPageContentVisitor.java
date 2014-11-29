package com.liusp.roommv.visitor.iwebPageVisitor;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.liusp.roommv.entity.webPage.WebPage;
import com.liusp.roommv.visitor.IWebPageContentVisitor;

public class WebPageContentVisitor implements IWebPageContentVisitor {
	private String content;
	private String title;
	private String body;

	public void visit(WebPage webPage) throws SQLException,
			UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Blob contentBlob = webPage.getContent();
		if (contentBlob != null) {
			content = new String(contentBlob.getBytes(1,
					(int) contentBlob.length()), "UTF-8");
		} else {
			content = "";
		}
	}

	public String parseTitle() throws ParserException {
		Parser parser = Parser.createParser(content, "UTF-8");
		NodeClassFilter filter1 = new NodeClassFilter(TitleTag.class);
		// CssSelectorNodeFilter filter2 = new CssSelectorNodeFilter(
		// ".article-title");
		// AndFilter filter3 = new AndFilter(filter1, filter2);
		NodeList nodeList = parser.extractAllNodesThatMatch(filter1);
		String title = null;
		for (int i = 0; i <= nodeList.size(); i++) {
			Node node = nodeList.elementAt(i);
			if (node != null) {
				return node.toPlainTextString();
			}
		}

		return title;
	}

	public String parseBody() throws ParserException {
		Parser parser = Parser.createParser(content, "UTF-8");
		// CssSelectorNodeFilter filter = new CssSelectorNodeFilter(
		// ".article-content");
		NodeClassFilter filter = new NodeClassFilter(BodyTag.class);
		NodeList nodeList = parser.extractAllNodesThatMatch(filter);
		String body = null;
		for (int i = 0; i <= nodeList.size(); i++) {
			Node node = nodeList.elementAt(i);
			if (node != null) {
				return node.toPlainTextString();
			}
		}
		return body;
	}
}
