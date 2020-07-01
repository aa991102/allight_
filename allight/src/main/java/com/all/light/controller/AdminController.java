package com.all.light.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdminController {
	
	//권한이 없을 시 접속 실패
	@RequestMapping("/fail")
	public ModelAndView connectFail(HttpSession session,ModelAndView mv,RedirectView rv) {
		System.out.println("AdminController.connectFail");
		mv.setViewName("common/admin/connectfail");
		return mv;
	}
	
	@RequestMapping("/admin")
	public ModelAndView adminHome(HttpSession session,ModelAndView mv,RedirectView rv) {
		System.out.println("AdminController.adminHome");
		mv.setViewName("common/admin/home");
		return mv;
	}
	
	@RequestMapping("/member/admin")
	public ModelAndView adminMember(HttpSession session,ModelAndView mv,RedirectView rv) {
		System.out.println("AdminController.adminMember");
		mv.setViewName("common/admin/member");
		return mv;
	}
	
	@RequestMapping("/corp/admin")
	public ModelAndView adminCorp(HttpSession session,ModelAndView mv,RedirectView rv) {
		System.out.println("AdminController.adminCorp");
		mv.setViewName("common/admin/corporation");
		return mv;
	}
	
	
}
