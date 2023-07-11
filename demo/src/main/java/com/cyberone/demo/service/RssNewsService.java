//package com.cyberone.demo.service;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.stereotype.Service;
//
//import com.cyberone.demo.model.request.rss.ReqRss;
//import com.cyberone.demo.model.request.rss.ReqRssAddr;
//import com.cyberone.demo.model.request.rss.ReqRssAddrList;
//import com.cyberone.demo.repository.RssNewsRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class RssNewsService {
//	
//	final RssNewsRepository rssNewsRepository;
//
//	public int addRss(ReqRss reqRss) {
//		if ( !String.valueOf(reqRss.getRegr()).equals("system") ) {
////			User user = ServletUtil.getSessionUser();
////			reqRss.setRegr(user.getAcct().getAcctId());
//		}
//		
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateTime = sdf.format(date);
//		
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("rssSrc", reqRss.getRssSrc());
//		paramMap.put("rssLink", reqRss.getRssLink());
//		paramMap.put("regr", reqRss.getRegr());
//		paramMap.put("modr", reqRss.getRegr());
//		paramMap.put("bbsSct", "R");
//		paramMap.put("regDtime", dateTime);
//		paramMap.put("modDtime", dateTime);
//		paramMap.put("bbsTit", reqRss.getBbsTit());
//		paramMap.put("bbsCont", reqRss.getBbsCont());
//		rssNewsRepository.insertBbsBase(paramMap);
//		
//		reqRss.setClippingYn("n");
//		int result = rssNewsRepository.insertRss(reqRss);
//		return result;
//	}
//
//
//	public List<Map<String, Object>> selectRssAddrList(ReqRssAddrList reqRssAddrList) {
//		reqRssAddrList.searchRowCount(rssNewsRepository.selectRssAddrCount(reqRssAddrList));
//		return rssNewsRepository.selectRssAddrList(reqRssAddrList);
//	}
//	
//
//	public int editRssAddr(ReqRssAddr reqRssAddr) {
//		if ( String.valueOf(reqRssAddr.getModr()).equals("system") ) {
//			reqRssAddr.setModr(null);
//		} else {
////			User user = ServletUtil.getSessionUser();
////			reqRssAddr.setModr(user.getAcct().getAcctId());
//		}
//		int result = rssNewsRepository.updateRssAddr(reqRssAddr);
//		return result;
//	}
//
//
//	public int editRssAddrCollect(String rssId, int collectCnt) {
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateTime = sdf.format(date);
//		
//		ReqRssAddr reqRssAddr = new ReqRssAddr();
//		reqRssAddr.setRssId(rssId);
//		reqRssAddr.setLastCollectDtime(dateTime);
//		reqRssAddr.setLastCollectCnt(collectCnt);
//		return rssNewsRepository.updateRssAddr(reqRssAddr);
//	}	
//	
//}
//
