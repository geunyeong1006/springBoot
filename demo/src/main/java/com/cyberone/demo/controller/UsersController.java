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

/**
 * 사용자 컨트롤러 클래스입니다.
 * 사용자 관련 기능을 제공합니다.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UsersController {
	/**
	 * 사용자 Service 객체입니다.
	 */
	private final UsersService usersService;
	
	/**
	 * 로그인 메서드입니다.
	 * @return 로그인 성공여부
	 */
	@PostMapping("/login")
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response){
		//로그인
		
		return usersService.login(request, response);
	}
	
	/**
	 * 회원가입 메서드입니다.
	 * @return 회원가입 성공여부
	 */
	@PostMapping("/signup")
	public Map<String, Object> signup(HttpServletRequest request, HttpServletResponse response) {
		//회원가입
		
		return usersService.signup(request, response);
		
	}
	
}
