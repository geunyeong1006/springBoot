package com.cyberone.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cyberone.demo.model.request.ReqLogin;
import com.cyberone.demo.model.response.ResLogin;

@Repository
@Mapper
public interface LoginRepository {
	ResLogin selectUsers(ReqLogin reqLogin);
}
