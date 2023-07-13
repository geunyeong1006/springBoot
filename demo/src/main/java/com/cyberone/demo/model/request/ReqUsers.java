package com.cyberone.demo.model.request;

import lombok.Data;

@Data
public class ReqUsers {
	
    private String id;
    
    private String userName;

    private String pw;
    
	private String email;
	
	private String regr;
	
	private String modr;
}
