package com.cyberone.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberone.demo.model.request.ReqUsers;
import com.cyberone.demo.model.response.ResUsers;
import com.cyberone.demo.service.HelloService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class HelloController {
	
	private final HelloService helloService;
	
    @PostMapping(value =  "/hello")
    public ResUsers hello(String id, String pw) {
    	System.out.print("아이디 : " + id + ", 비밀번호: " + pw);
    	ReqUsers reqLogin = new ReqUsers();
    	ResUsers resLogin = new ResUsers();
    	reqLogin.setId(id);
    	reqLogin.setPw(pw);
    	resLogin = helloService.getUsers(reqLogin);
        return resLogin;
    }
}
