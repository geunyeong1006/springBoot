package com.cyberone.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberone.demo.model.request.ReqLogin;
import com.cyberone.demo.model.response.ResLogin;
//import com.cyberone.demo.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {
//	private final LoginService loginService;
	
	@PostMapping(value="/verifyAccoount")
	public String VerifyAccoount(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		
		return "/";
		
	}
	
	@PostMapping(value="/login")
	public String Login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		String androidID = request.getParameter("id");
		String androidPW = request.getParameter("pw");
		ReqLogin reqLogin = new ReqLogin();
    	ResLogin resLogin = new ResLogin();
    	reqLogin.setId(androidID);
    	reqLogin.setPw(androidPW);
//    	resLogin = loginService.selectUsers(reqLogin);
		
		
		return "good";
		
	}
}
