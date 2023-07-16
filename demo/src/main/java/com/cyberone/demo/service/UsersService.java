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

/**
 * 사용자 서비스 클래스입니다.
 * 사용자 관련 기능을 제공합니다.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UsersService {
	
	/**
	 * 사용자 DAO 객체입니다.
	 */
	private final UsersDao usersDao;

	
	
	/**
	 * 로그인 메서드입니다.
	 * @return 로그인 성공여부
	 */
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		map.put("success", "");
		String androidID = request.getParameter("id");
		String androidPW = request.getParameter("pw");
		ReqUsers reqUser = new ReqUsers();
    	reqUser.setId(androidID);
    	reqUser.setPw(androidPW);
    	
    	ResUsers resUser = usersDao.login(reqUser);
		
		if(resUser != null) {
			usersDao.updateUser(reqUser);
			map.put("success", resUser);
		}else {
			map.put("fail", "아이디 또는 비밀번호를 잘못 입력했습니다.\r\n"
					+ "입력하신 내용을 다시 확인해주세요.");
		}
    	
    	
		return map;
	}
	
	/**
	 * 회원가입 메서드입니다.
	 * @return 회원가입 성공여부
	 */
	public Map<String, Object> signup(HttpServletRequest request, HttpServletResponse response) {		//회원가입
		Map<String, Object> map = new HashMap<>();
		map.put("success", "");
		String message = "";
		String androidID = request.getParameter("id");
		String androidUSERNAME = request.getParameter("username");
		String androidPW = request.getParameter("pw");
		String androidEMAIL = request.getParameter("email");
		ReqUsers reqUser = new ReqUsers();
		reqUser.setId(androidID);
		if (androidUSERNAME != null && !"".equals(androidUSERNAME)) {
			reqUser.setUserName(androidUSERNAME);
		}else {
			reqUser.setUserName(androidID);
		}
		reqUser.setPw(androidPW);
		reqUser.setEmail(androidEMAIL);
		reqUser.setRegr(androidID);
		ResUsers resUser = selectUser(request, response);
		
		if(resUser != null) {
			message = "이미 가입된 id입니다.";
			if(resUser.getEmail().length()> 0) {
				message = "email 주소가 이미 있습니다";
			}
			map.put("fail", message);
			return map;
		}
		
		
		int result = usersDao.signup(reqUser);
		if(result > 0) {
			message = "회원가입 성공";
		}else{
			message = "회원가입 실패";
			map.put("fail", message);
		}
		return map;
	}
	
	/**
	 * 사용자 정보를 조회하는 메서드입니다.
	 * @return 사용자 정보
	 */
	public ResUsers selectUser(HttpServletRequest request, HttpServletResponse response) {
		String androidID = request.getParameter("id");
		return usersDao.selectUser(androidID);
	}
	
	/**
	 * 모든 사용자 정보를 조회하는 메서드입니다.
	 * @return 모든 사용자 정보 리스트
	 */
	public List<Map<String, Object>> selectAllUser() {
		
		return usersDao.selectAllUser();
	}
}
