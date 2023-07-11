package com.cyberone.demo.model.request.rss;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class ReqRssAddr {
	
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
	
	@ApiParam(value = "마지막수집일시")
	private String lastCollectDtime;
	
	@ApiParam(value = "마지막수집건수")
	private int lastCollectCnt;

}
