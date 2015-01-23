package com.liusp.roommv.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import com.liusp.roommv.base.BaseTest;
import com.liusp.roommv.common.RoommvConstant;
import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.service.htmlInfo.IHtmlInfoService;
import com.liusp.roommv.vo.VisitInfo;

public class HtmlInfoControllerTest extends BaseTest {
	@Resource(name = "htmlInfoService")
	private IHtmlInfoService htmlInfoService;

	@Test
	public void test() {
		try {
			HtmlInfo htmlInfo = new HtmlInfo();
			htmlInfo.setId(UUID.randomUUID().toString());
			htmlInfo.setContent("<p>asdsadasdsa</p>");
			htmlInfo.setCreateDate(new Date());
			htmlInfo.setAuditStatus(RoommvConstant.AuditStatus.AUDIT_ING.getCode());
			htmlInfo.setCreator("asd");
			htmlInfo.setHtmlId("43456780987865443");
			htmlInfo.setTitle("测试");
			htmlInfoService.save(htmlInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getHtml() {
		try {
			Object object = htmlInfoService
					.getHtmlInfoById("27b5fef3-56be-4121-b90e-b8c7dc2946f4");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// System.out.println(UUID.randomUUID().toString());
	// }
	public static void main(String[] args) throws IOException {

		VisitInfo visitInfo = new VisitInfo();
		visitInfo.setCurrentUrl("aa");
		visitInfo.setIp("a");
		visitInfo.setReferUrl("a");
		visitInfo.setUserId("a");
		visitInfo.setCreateDate("sa");
		System.out.println(visitInfo.getCreateDate());

	}
}
