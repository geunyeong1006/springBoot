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

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class RssNewsController {
	
	private final RssNewsService rssNewsService;
	
	@GetMapping(value = "/newsClipping")
	public List<Map<String, Object>> newsClippingList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String date = request.getParameter("regDdate");
		return rssNewsService.selectRssList(date);
	}
	
	@GetMapping(value = "/newsClippingDetail")
	public List<Map<String, Object>> newsClippingDetailList(HttpServletRequest request, HttpServletResponse response, Model model) {
		String date = request.getParameter("regDdate");
		return rssNewsService.selectRssDetailList(date);
	}

}
