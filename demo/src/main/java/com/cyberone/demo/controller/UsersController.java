package com.cyberone.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberone.demo.model.request.ReqUsers;
import com.cyberone.demo.model.response.ResUsers;
import com.cyberone.demo.service.UsersService;
import com.cyberone.demo.utils.Encrypt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class UsersController {
	private final UsersService usersService;
	private final Encrypt encrypt;
	
	@PostMapping(value="/verifyAccoount")
	public String VerifyAccoount(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		
		return "/";
		
	}
	
	@PostMapping(value="/login")
	public ResUsers Login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		String androidID = request.getParameter("id");
		String androidPW = request.getParameter("pw");
		ReqUsers reqUser = new ReqUsers();
    	ResUsers resUser = new ResUsers();
    	reqUser.setId(androidID);
    	reqUser.setPw(androidPW);
    	resUser = usersService.getUsers(reqUser);
		
		return resUser;
		
	}
	
	@PostMapping(value = "/signup")
	public Map<String, Object> signup(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("success", "");
		int result = 0;
		String message = "";
		String androidID = request.getParameter("id");
		String androidUSERNAME = request.getParameter("userName");
		String androidPW = request.getParameter("pw");
		String androidEMAIL = request.getParameter("email");
		ReqUsers reqUser = new ReqUsers();
		ResUsers resUser = new ResUsers();
		reqUser.setId(androidID);
		reqUser.setUserName(androidUSERNAME);
		reqUser.setPw(encrypt.getEncrypt(androidPW, encrypt.getSalt()));
		reqUser.setEmail(androidEMAIL);
		resUser = usersService.confirmUser(androidID);
		
		if(resUser != null) {
			message = "이미 가입된 id입니다.";
			if(resUser.getUserSt() == 1) {
				message = "관리자의 승인을 기다리는 중입니다.";
			}else if(resUser.getUserSt() == 3) {
				message = "정지된 id입니다.";
			}
			if(resUser.getEmail().length()> 0) {
				message = "email 주소가 이미 있습니다";
			}
			map.put("fail", message);
			return map;
		}
		
		
		result = usersService.signup(reqUser);
		if(result > 0) {
			message = "회원가입 성공";
		}else{
			message = "회원가입 실패";
			map.put("fail", message);
		}
		return map;
		
	}
	
	@PostMapping(value="/verifySignup")
	public String VerifySignup(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		
		return "/";
		
	}
}
