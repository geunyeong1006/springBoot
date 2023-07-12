package com.cyberone.demo.service;

import org.springframework.stereotype.Service;

import com.cyberone.demo.dao.HelloDao;
import com.cyberone.demo.model.request.ReqUsers;
import com.cyberone.demo.model.response.ResUsers;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HelloService {
	
	final HelloDao helloDao;
	
	public ResUsers getUsers(ReqUsers reqLogin) {
		return helloDao.getUsers(reqLogin);
	}
}
