package com.cyberone.demo.model.request.rss;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class ReqRss extends ReqBbsBase {
	
	private String[] bbsIdStr;
	
	@ApiParam(value = "출처")
	private String rssSrc;	
	
	@ApiParam(value = "링크")
	private String rssLink;
	
	@ApiParam(value = "클리핑여부")
	private String clippingYn;
	
	@ApiParam(value = "발송뉴스예약선택")
	private String rsvYn;
	
	@ApiParam(value = "월간뉴스예약선택")
	private String monthYn;
	
	public void validate() throws Exception {
		
		BeanInfo beanInfo = Introspector.getBeanInfo(ReqRss.class);
		for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
			Method m = pd.getReadMethod();
			if (String.valueOf(m.invoke(this)).equals("undefined") || String.valueOf(m.invoke(this)).equals("null")) {
				m = pd.getWriteMethod();
				m.invoke(this, "");
			}
		}

		if (bbsTit.isEmpty()) {
			throw new Exception("제목을 입력하세요.");
		}

		if (bbsCont.isEmpty()) {
			throw new Exception("본문을 입력하세요.");
		}

		bbsTit = HtmlUtils.htmlUnescape(StringEscapeUtils.unescapeHtml(bbsTit));
		bbsCont = HtmlUtils.htmlUnescape(StringEscapeUtils.unescapeHtml(bbsCont));
	}
	

}
