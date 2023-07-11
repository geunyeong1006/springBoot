package com.cyberone.demo.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * REST API Response
 * @param <T>
 */
@Data
public class ResPaging {

    @ApiModelProperty(value = "페이지번호", required = false, hidden = true)
    private int page;
    
    
    @ApiModelProperty(value = "페이지당 목록갯수", required = false, hidden = true)
    private int rows;
    
    @ApiModelProperty(value = "페이지 번호갯수", required = false, hidden = true)
    private int pageCount;
    
    @ApiModelProperty(value = "전체 갯수", required = false, hidden = true)
    private int records;
    
}

