package com.cyberone.demo.service;

import org.springframework.stereotype.Service;

import com.cyberone.demo.model.request.ReqLogin;
import com.cyberone.demo.model.response.ResLogin;
import com.cyberone.demo.repository.HelloRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HelloService{
	
	final HelloRepository helloRepository;
	
	public ResLogin selectUsers(ReqLogin reqLogin) {
		return helloRepository.selectUsers(reqLogin);
	}
}
