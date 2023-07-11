package com.cyberone.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class HomeController {
	// 안드로이드 요청(id, pw 받아서 db연동)
	@RequestMapping(value= "/android")
	public String androidPage(HttpServletRequest request, Model model) {
		System.out.println("서버에서 안드로이드 접속 요청함");
		try{
				String androidID = request.getParameter("id");
				String androidPW = request.getParameter("pw");
				System.out.println("안드로이드에서 받아온 id : " + androidID);
				System.out.println("안드로이드에서 받아온 pw : " + androidPW);
				model.addAttribute("android", androidID);
				return "android";
		}catch (Exception e){
				e.printStackTrace();
				return "null";
		}
	}
}
