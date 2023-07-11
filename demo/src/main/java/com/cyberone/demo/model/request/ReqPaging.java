package com.cyberone.demo.model.request;

import com.cyberone.demo.model.response.ResPaging;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * REST API Response
 * @param <T>
 */
@Data
public class ReqPaging {

    @ApiParam(value = "페이지번호", defaultValue = "1", required = true)
    private int page;
    
    @ApiParam(value = "페이지당 목록갯수", defaultValue = "10", required = true)
    private int rows;

    @ApiParam(value = "전체 페이지갯수", required = false, hidden = true)
    private int pageCount;

    @ApiParam(value = "시작목록", required = false, hidden = true)
    private int limit;

    @ApiParam(value = "페이지그룹위치", required = false, hidden = true)
	private int group;
    
    @ApiParam(value = "그룹당페이지수", required = false, hidden = true)
	private int groupPage;
    
    @ApiParam(value = "그룹시작위치", required = false, hidden = true)
	private int groupStart;
    
    @ApiParam(value = "그룹마지막위치", required = false, hidden = true)
	private int groupEnd;
    
    @ApiParam(value = "이전페이지", required = false, hidden = true)
	private int prev;
    
    @ApiParam(value = "다음페이지", required = false, hidden = true)
	private int next;
    
    @ApiParam(value = "정렬컬럼")
	private String sidx;
	
    @ApiParam(value = "정렬순서")
	private String sord;
	
    @ApiParam(value = "페이징정보")
	private ResPaging resPaging;
	
	public void searchRowCount(int records) {

		if(records > 0 && rows > 0){
			pageCount += (records/rows) + ((records%rows > 0) ? 1 : 0);
		}
		page = (page < 1) ? 1 : page;
		limit = rows * (page - 1);
		
		resPaging = new ResPaging();
		resPaging.setRows(rows);
		resPaging.setRecords(records);
		resPaging.setPageCount(pageCount);
		resPaging.setPage(page);
	}
    
}

