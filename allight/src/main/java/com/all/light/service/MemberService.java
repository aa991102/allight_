package com.all.light.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.all.light.dao.MemberDAO;
import com.all.light.dto.MemberDTO;

public class MemberService {
	@Autowired
	private MemberDAO memDAO;
	
	public void login(MemberDTO memdto, HttpSession session) {
		System.out.println("MemberService");
		//일반로그인
		HashMap map=new HashMap();
			map.put("mid",memdto.getMid());
			map.put("mpw",memdto.getMpw());
			HashMap result=memDAO.login(map);
			if(result==null || result.size()==0) {
				//로그인실패
				System.out.println("로그인실패");
			}else{
				//로그인성공
				System.out.println("로그인성공");
				session.setAttribute("MID",result.get("MID"));
		}
		
		//카카오로그인
	}

}
