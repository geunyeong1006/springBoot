package com.cyberone.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberone.demo.model.request.ReqLogin;
import com.cyberone.demo.model.response.ResLogin;
import com.cyberone.demo.service.HelloService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class HelloController {
	private HelloService helloService;
	
    @PostMapping("/hello")
    public String hello(String id, String pw) {
    	System.out.print("아이디 : " + id + ", 비밀번호: " + pw);
    	ReqLogin reqLogin = new ReqLogin();
    	ResLogin resLogin = new ResLogin();
    	reqLogin.setId(id);
    	reqLogin.setPw(pw);
    	resLogin = helloService.selectUsers(reqLogin);
        return "Hello, World!";
    }
}
