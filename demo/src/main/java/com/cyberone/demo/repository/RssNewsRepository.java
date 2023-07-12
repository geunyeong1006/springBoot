package com.cyberone.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cyberone.demo.model.request.rss.ReqRss;
import com.cyberone.demo.model.request.rss.ReqRssAddr;
import com.cyberone.demo.model.request.rss.ReqRssAddrList;

@Repository
@Mapper
public interface RssNewsRepository {

	public int updateRssAddr(ReqRssAddr reqRssAddr);
	
	public int insertBbsBase(Map<String, Object> paramMap);

	
}
