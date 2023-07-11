package com.cyberone.demo.model.request.rss;

import com.cyberone.demo.model.request.ReqPaging;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class ReqRssAddrList extends ReqPaging {
	
	@ApiParam(value = "RSS주소아이디")
	private String rssId;
	
	@ApiParam(value = "RSS출처")
	private String rssSrc;
	
	@ApiParam(value = "RSS주소")
	private String rssAddr;
	
	@ApiParam(value = "파라미터명")
	private String guidParam;
	
	@ApiParam(value = "마지막수집값")
	private String guidLast;
	
	@ApiParam(value = "등록자")
	private String regr;
	
	@ApiParam(value = "등록일시")
	private String regDtime;
	
	@ApiParam(value = "수정자")
	private String modr;
	
	@ApiParam(value = "수정일시")
	private String modDtime;	
	
	@ApiModelProperty("검색구분")
	private String srchSct;

	@ApiModelProperty("검색내용")
	private String srchCont;

}
