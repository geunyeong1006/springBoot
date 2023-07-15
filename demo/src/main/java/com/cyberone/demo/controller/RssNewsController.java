package com.cyberone.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberone.demo.service.RssNewsService;

import lombok.RequiredArgsConstructor;

/**
 * Rss 컨트롤러 클래스입니다.
 * Rss 관련 기능을 제공합니다.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RssNewsController {
	/**
	 * Rss Service 객체입니다.
	 */
	private final RssNewsService rssNewsService;
	
	/**
	 * 일자별 뉴스를 확인하는 서비스입니다.
	 */
	@GetMapping("/selectNewsList")
	public List<Map<String, Object>> selectNewsList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String date = request.getParameter("regDday");
		return rssNewsService.selectNewsList(date);
	}
	
	/**
	 * 헤딩일자의 뉴스를 확인하는 서비스입니다.
	 */
	@GetMapping("/selectNewsDetailList")
	public List<Map<String, Object>> selectNewsDetailList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String date = request.getParameter("regDday");
		return rssNewsService.selectNewsDetailList(date);
	}
	
	/**
	 * 계정의 알람리스트를 확인하는 서비스입니다.
	 */
	@GetMapping("/selectNewsAlarmList")
	public List<Map<String, Object>> newsAlarmList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		return rssNewsService.selectNewsAlarmList(id);
	}
	
	/**
	 * 계정의 알람을 수정하는 서비스입니다.
	 */
	@GetMapping("/updateNewsAlarm")
	public int updatenewsAlarmList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		String alarmdate = request.getParameter("alarmdate");
		return rssNewsService.updateNewsAlarm(id, alarmdate);
			
	}

}
