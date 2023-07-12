package com.cyberone.demo.controller;

import java.util.List;
import java.util.Map;

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
	public List<Map<String, Object>> newsClippingList() {
		return rssNewsService.selectRssList();
	}

}
