package com.cyberone.demo.model.request.rss;

import com.cyberone.demo.model.request.ReqPaging;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqRssList extends ReqPaging {
	
	@ApiModelProperty("게시판아이디")
	private String bbsId;
	
	private String bbsSct;
	private String bbsIdStr;
	
	@ApiModelProperty("출처")
	private String rssSrc;
	
	@ApiModelProperty("링크")
	private String rssLink;
	
	@ApiModelProperty("클리핑여부")
	private String clippingYn;
	
	@ApiModelProperty("발송뉴스예약선택")
	private String rsvYn;
	
	@ApiModelProperty("월간뉴스예약선택")
	private String monthYn;
	
	private String modDtime;
	
	@ApiModelProperty("시작일자")
	private String startDtime;

	@ApiModelProperty("종료일자")
	private String endDtime;
	
	@ApiModelProperty("검색구분")
	private String srchSct;

	@ApiModelProperty("검색내용")
	private String srchCont;	

}
