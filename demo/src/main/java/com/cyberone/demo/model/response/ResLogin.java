package com.cyberone.demo.model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResLogin {
    private int id;

    private String email;
    private String pw;
}
