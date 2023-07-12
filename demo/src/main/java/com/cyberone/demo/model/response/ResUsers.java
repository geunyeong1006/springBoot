package com.cyberone.demo.model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResUsers {
	
    private String id;

    private String userName;
    
    private String email;
    
    private String pw;
    
    private int userTp;
    
    private int userSt; 
}
