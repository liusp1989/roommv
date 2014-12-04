package com.liusp.roommv.service.htmlInfo;

import java.io.Serializable;

import com.liusp.roommv.entity.html.HtmlInfo;
import com.liusp.roommv.service.base.IBaseService;

public interface IHtmlInfoService<T, PK extends Serializable> extends
		IBaseService<T, PK> {

	void saveOrupdateObject(HtmlInfo htmlInfo);

}
