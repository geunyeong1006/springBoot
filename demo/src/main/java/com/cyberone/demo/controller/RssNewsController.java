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
	
	@GetMapping("/selectNewsList")
	public List<Map<String, Object>> selectNewsList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String date = request.getParameter("regDday");
		return rssNewsService.selectNewsList(date);
	}
	
	@GetMapping("/selectNewsDetailList")
	public List<Map<String, Object>> selectNewsDetailList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String date = request.getParameter("regDday");
		return rssNewsService.selectNewsDetailList(date);
	}
	
	@GetMapping("/selectNewsAlarmList")
	public List<Map<String, Object>> newsAlarmList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		return rssNewsService.selectNewsAlarmList(id);
	}
	
	@GetMapping("/updatenewsAlarm")
	public List<Map<String, Object>> updatenewsAlarmList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		String alarmdate = request.getParameter("alarmdate");
		rssNewsService.updateNewsAlarm(id, alarmdate);
			
		return rssNewsService.selectNewsAlarmList(id);
	}

}
