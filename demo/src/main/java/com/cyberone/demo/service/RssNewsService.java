package com.cyberone.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cyberone.demo.dao.RssNewsDao;
import com.cyberone.demo.model.request.rss.ReqRss;
import com.cyberone.demo.model.request.rss.ReqRssAddr;
import com.cyberone.demo.model.request.rss.ReqRssAddrList;

import lombok.RequiredArgsConstructor;

/**
 * Rss 서비스 클래스입니다.
 * Rss 관련 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RssNewsService {
	/**
	 * Rss DAO 객체입니다.
	 */
	private final RssNewsDao rssNewsDao;
	
	/**
	 * 사용자 서비스 객체입니다.
	 */
	private final UsersService usersService;
	
	/**
	 * Rss추가 및 알람등록 메서드입니다.
	 * @return Rss추가 성공여부
	 */
	public int addRss(ReqRss reqRss) {
		if (!"system".equals(String.valueOf(reqRss.getRegr()))) {
		    // reqRss.getRegr()이 "system"이 아닌 경우의 로직 처리
//			User user = ServletUtil.getSessionUser();
//			reqRss.setRegr(user.getAcct().getAcctId());
		}
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateTime = currentDateTime.format(formatter);
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today =  currentDate.format(formatter2);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rssSrc", reqRss.getRssSrc());
		paramMap.put("rssLink", reqRss.getRssLink());
		paramMap.put("regr", reqRss.getRegr());
		paramMap.put("modr", reqRss.getRegr());
		paramMap.put("bbsSct", "R");
		paramMap.put("regDtime", dateTime);
		paramMap.put("modDtime", dateTime);
		paramMap.put("bbsTit", reqRss.getBbsTit());
		paramMap.put("bbsCont", reqRss.getBbsCont());
		
		
		List<Map<String, Object>> allUserList = usersService.selectAllUser();
		for (Map<String, Object> userResult : allUserList) {
			 String loginDate = userResult.get("modDtime").toString();
			 DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			 DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 

			 LocalDateTime localDateTime = LocalDateTime.parse(loginDate, inputFormatter);
			 String loginDay = localDateTime.format(outputFormatter);
			 
			 LocalDate loginLocalDate = LocalDate.parse(loginDay);
		     LocalDate todayLocalDate = LocalDate.parse(today);
		     
		     Map<String, Object> rssAlarm = rssNewsDao.selectNewsAlarm(userResult.get("id").toString(), today);
		     
		     if(rssAlarm == null && loginLocalDate.isBefore(todayLocalDate)) {
		    	 rssNewsDao.insertRssAlarm(userResult.get("id").toString(), today);
		     }
		}
		rssNewsDao.insertBbsBase(paramMap);
		
		
		reqRss.setClippingYn("n");
		return rssNewsDao.insertRss(reqRss);
	}

	
	/**
	 * Rss주소 조회 메서드입니다.
	 * @return Rss주소 리스트
	 */
	public List<Map<String, Object>> selectRssAddrList(ReqRssAddrList reqRssAddrList) {
		return rssNewsDao.selectRssAddrList(reqRssAddrList);
	}
	
	/**
	 * Rss주소 수정 메서드입니다.
	 * @return Rss수정 성공여주
	 */
	public int editRssAddr(ReqRssAddr reqRssAddr) {
		if (!"system".equals(String.valueOf(reqRssAddr.getRegr()))) {
			reqRssAddr.setModr(null);
		} else {
//			User user = ServletUtil.getSessionUser();
//			reqRssAddr.setModr(user.getAcct().getAcctId());
		}
		int result = rssNewsDao.updateRssAddr(reqRssAddr);
		return result;
	}

	/**
	 * Rss주소 수정 메서드입니다.
	 * @return Rss수정 성공여주
	 */
	public int editRssAddrCollect(String rssId, int collectCnt) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = sdf.format(date);
		
		ReqRssAddr reqRssAddr = new ReqRssAddr();
		reqRssAddr.setRssId(rssId);
		reqRssAddr.setLastCollectDtime(dateTime);
		reqRssAddr.setLastCollectCnt(collectCnt);
		return rssNewsDao.updateRssAddr(reqRssAddr);
	}	
	
	/**
	 * Rss리스트 조회 메서드입니다.
	 * @return Rss리스트
	 */
	public List<Map<String, Object>> selectNewsList(String date){
		
		return rssNewsDao.selectNewsList(date);
		
	}
	
	/**
	 * Rss알람 등록 메서드입니다.
	 * @return Rss알람 성공여부
	 */
	public int insertRssAlarm(String id, String alarmdate) {
		return rssNewsDao.insertRssAlarm(id, alarmdate);
	}
	
	/**
	 * Rss알람 조회 메서드입니다.
	 * @return Rss알람 성공여부
	 */
	public List<Map<String, Object>> selectNewsAlarmList(String id){
		return rssNewsDao.selectNewsAlarmList(id);
	}
	
	public List<Map<String, Object>> selectNewsDetailList(String date){
		
		return rssNewsDao.selectNewsDetailList(date);
		
	}
	
	public int updateNewsAlarm(String id, String alarmdate) {
		
		return rssNewsDao.updateNewsAlarm(id, alarmdate);
	}
	
}

