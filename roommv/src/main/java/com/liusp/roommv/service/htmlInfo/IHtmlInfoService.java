package com.liusp.roommv.service.htmlInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.entity.html.HtmlTemplate;

public interface IHtmlInfoService {
	void auditHtmlInfo(HtmlInfo orightmlInfo) throws IOException;

	void save(HtmlInfo htmlInfo) throws Exception;

	void saveOrUpdateHtmlInfo(HtmlInfo htmlInfo) throws Exception;

	List<HtmlInfo> getHtmlInfos(Map<String, Object> criteria);

	void deleteHtmlInfo(HtmlInfo htmlInfo);

	HtmlInfo getHtmlInfoById(String id);

	void updateHtmlInfo(HtmlInfo htmlInfo);

	List<HtmlTemplate> getHtmlTemplate(Map<String, Object> criteria);

	void saveAndWriteFile(HtmlInfo htmlInfo) throws Exception;

}
