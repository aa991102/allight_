package com.all.light.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.all.light.dto.MemberDTO;
import com.all.light.util.PageUtil;


public class MemberDAO extends SqlSessionDaoSupport{
	@Autowired
	SqlSessionTemplate session;
	
	public HashMap login(HashMap map) {
		System.out.println("MemberDAO : "+map.toString());
		HashMap result=session.selectOne("member.login", map);
		return result;
	}

	public HashMap kakao(MemberDTO memdto) {
		System.out.println("UserDAO");
		System.out.println(memdto.getMid());
		HashMap result=session.selectOne("member.kakao", memdto);
		System.out.println("result"+result);
		return result;
	}

	public HashMap join(MemberDTO memdto) {
		System.out.println("UserDAO join");
		System.out.println(memdto);
		//수정삭제가 만들어진후에 변경
		int i=session.insert("member.kinsert", memdto);
		System.out.println(i);
		HashMap res=null;
		if(i==1) {
			res=session.selectOne("member.kakao", memdto);
		}
		return res;
	}

	//유태우7.2작성
	public ArrayList list(PageUtil pInfo) {
		System.out.println("MemberDAO.list = "+pInfo.toString());
		return (ArrayList) session.selectList("member.list", pInfo);
	}

	public int getTotalCnt() {
		System.out.println("MemberDAO.getTotalCnt");
		System.out.println("totalCnt = " + session.selectOne("member.totalCnt"));
		int totalCnt = session.selectOne("member.totalCnt");
		System.out.println("totalCnt = " + totalCnt);
		return totalCnt;
	}
}
