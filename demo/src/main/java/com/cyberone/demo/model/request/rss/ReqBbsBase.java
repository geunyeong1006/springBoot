package com.cyberone.demo.model.request.rss;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class ReqBbsBase {
	
	@ApiParam(value = "게시글아이디")
	protected String bbsId;
	
	@ApiParam(value = "게시글제목")
	protected String bbsTit;
	
	@ApiParam(value = "게시글내용")
	protected String bbsCont;
	
	@ApiParam(value = "조회수")
	private String qryCnt;
	
	@ApiParam(value = "게시판구분")
	private String bbsSct;
	
	@ApiParam(value = "파일첨부여부")
	private String bbsFileYn;
	
	@ApiParam(value = "등록자")
	private String regr;
	
	@ApiParam(value = "등록일시")
	private String regDtime;
	
	@ApiParam(value = "수정자")
	private String modr;
	
	@ApiParam(value = "수정일시")
	private String modDtime;
	
	@ApiParam(value = "발표일")
	private String notiDtime;
	
	@ApiParam(value = "메일전송여부")
	protected String mailSendYn;
	
	@ApiParam(value = "처리상태")
	private String procSt;	

}
