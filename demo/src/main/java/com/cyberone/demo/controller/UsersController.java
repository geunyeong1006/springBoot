package com.cyberone.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberone.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class UsersController {
	private final UsersService usersService;
	
	@PostMapping(value="/verifyAccoount")
	public String VerifyAccoount(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		
		return "/";
		
	}
	
	@PostMapping(value="/login")
	public Map<String, Object> Login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		return usersService.login(request, response);
	}
	
	@PostMapping(value = "/signup")
	public Map<String, Object> signup(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return usersService.signup(request, response);
		
	}
	
	@PostMapping(value="/verifySignup")
	public String VerifySignup(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		
		return "/";
		
	}
}
