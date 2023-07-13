package com.cyberone.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cyberone.demo.dao.UsersDao;
import com.cyberone.demo.model.request.ReqUsers;
import com.cyberone.demo.model.response.ResUsers;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class UsersService {
	
	final UsersDao usersDao;
	
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		map.put("success", "");
		String message = "";
		String androidID = request.getParameter("id");
		String androidPW = request.getParameter("pw");
		ReqUsers reqUser = new ReqUsers();
    	reqUser.setId(androidID);
    	reqUser.setPw(androidPW);
    	ResUsers resUser = new ResUsers();
    	
    	resUser = confirmUser(request, response);
		
		if(resUser != null) {
			if(resUser.getUserSt() == 1) {
				message = "관리자의 승인을 기다리는 중입니다.";
				map.put("fail", message);
				return map;
			}else if(resUser.getUserSt() == 3) {
				message = "정지된 id입니다.";
				map.put("fail", message);
				return map;
			}
		}
		
		resUser = usersDao.login(reqUser);
		
		if(resUser != null) {
			usersDao.updateUser(reqUser);
			map.put("success", resUser);
		}else {
			map.put("fail", "오류");
		}
    	
    	
		return map;
	}
	
	public Map<String, Object> signup(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		map.put("success", "");
		int result = 0;
		String message = "";
		String androidID = request.getParameter("id");
		String androidUSERNAME = request.getParameter("username");
		String androidPW = request.getParameter("pw");
		String androidEMAIL = request.getParameter("email");
		ReqUsers reqUser = new ReqUsers();
		ResUsers resUser = new ResUsers();
		reqUser.setId(androidID);
		if (androidUSERNAME != "" && androidUSERNAME != null) {
			reqUser.setUserName(androidUSERNAME);
		}else {
			reqUser.setUserName(androidID);
		}
		reqUser.setPw(androidPW);
		reqUser.setEmail(androidEMAIL);
		reqUser.setRegr(androidID);
		resUser = confirmUser(request, response);
		
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
		}
		
		
		result = usersDao.signup(reqUser);
		if(result > 0) {
			message = "회원가입 성공";
		}else{
			message = "회원가입 실패";
			map.put("fail", message);
		}
		return map;
	}
	
	public ResUsers confirmUser(HttpServletRequest request, HttpServletResponse response) {
		String androidID = request.getParameter("id");
		return usersDao.confirmUser(androidID);
	}
	
	public List<Map<String, Object>> selectAllUser() {
		
		return usersDao.selectAllUser();
	}
}
