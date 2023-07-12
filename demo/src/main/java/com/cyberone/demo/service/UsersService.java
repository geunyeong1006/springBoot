package com.cyberone.demo.service;

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
	
	public ResUsers getUsers(ReqUsers reqUser) {
		return usersDao.getUsers(reqUser);
	}
	
	public int signup(ReqUsers reqUsers) {
		return usersDao.signup(reqUsers);
	}
	
	public ResUsers confirmUser(String id) {
		return usersDao.confirmUser(id);
	}
}
