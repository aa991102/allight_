package com.all.light.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.all.light.dto.FreeBoardDTO;
import com.all.light.dto.NoticeDTO;
import com.all.light.dto.QuestionDTO;
import com.all.light.service.FreeBoardService;
import com.all.light.util.FileUtil;
import com.all.light.util.PageUtil;

@Controller
public class FreeBoardController {
	@Autowired
	private FreeBoardService freSVC;
	
	//유저단
	@RequestMapping(value="/freeboard/write", method= RequestMethod.GET)
	public ModelAndView freeBoardWriteGet(
			HttpServletRequest request, ModelAndView mv){
		System.out.println("FreeBoardController.freeBoardWrite" + request.getMethod() + "method");
		mv.setViewName("diary/user/freeboard/boardWrite");
		return mv;
	}
	
	@RequestMapping(value="/freeboard/write", method= RequestMethod.POST)
	public ModelAndView freeBoardWritePost(
			HttpServletRequest request, ModelAndView mv, RedirectView rv, FreeBoardDTO fdto){
		System.out.println("FreeBoardController.freeBoardWrite" + request.getMethod() + "method");
		String id = (String)request.getSession().getAttribute("MID");
		String nick = (String)request.getSession().getAttribute("MNICK");
		//파라미터 전달
		fdto.setFid(id);
		fdto.setFnick(nick);
		
		// - 첨부파일 처리
		String path="D:\\upload";
		ArrayList list= new ArrayList();
		for(int i=0; i< fdto.getFiles().length ;i++ ) { //파일이 여러 개이니 각각의 파일마다 반복
			//한 개씩 파일의 실제이름을 알아낸다
			String oriName = 
			  fdto.getFiles()[i].getOriginalFilename();
			//파일이 업로드 되지 않으면 다음 작업을 시도한다
			if( oriName==null||oriName.length()==0 ) {
				continue;
			}
			String saveName = FileUtil.renameTo(path, oriName);
			File file= new File(path, saveName);
			//파일복사 : transferTo()
			try {
				fdto.getFiles()[i].transferTo(file);
			}catch(Exception e) {
				System.out.println("파일복사관련에러="+e);
			}
			//------------ 하나의 파일이 업로드된 상태
			//업로드된 파일의 정보를 Map으로 묶는다
			HashMap map = new HashMap();
			map.put("path",    path);
			map.put("oriName", oriName);
			map.put("saveName",saveName);
			map.put("len", file.length());
			list.add(map);
		}//end for
			
		System.out.println("파라미터 = "+fdto);
		//비즈니스로직
		freSVC.write(fdto,list);
		//뷰지정
		rv.setUrl(request.getContextPath() + "/freeboard/list.com"); 
		mv.setView(rv);
		return mv;
	}
	
	@RequestMapping(value="/freeboard/list")
	public ModelAndView freeBoardList(
			@RequestParam(value = "nowPage", required = false, defaultValue = "1") int nowPage,
			@RequestParam(value = "search", required = false, defaultValue = "") String searchWord,
			@RequestParam(value = "type", required = false, defaultValue = "fall") String searchType,
			@RequestParam(value = "ftype", required = false, defaultValue = "") String ftype,
			HttpServletRequest request, ModelAndView mv){
		System.out.println("FreeBoardController.freeBoardList" + request.getMethod() + "method");
		
		//비즈니스로직
		PageUtil pInfo = freSVC.getPageInfo(nowPage, searchWord, searchType, ftype);
		ArrayList<FreeBoardDTO> map = freSVC.searchList(pInfo, searchWord, searchType, ftype);

		System.out.println("list = " + map.toString());
		System.out.println("pinfo = " + pInfo.toString());
		
		//모델
		mv.addObject("LIST", map); // 공지 리스트
		mv.addObject("PINFO", pInfo); // 페이징 정보
		
		//뷰 지정
		mv.setViewName("diary/user/freeboard/boardList");
		return mv;
	}
	
	@RequestMapping(value="/freeboard/detail")
	public ModelAndView freeBoardDetail(
			@RequestParam(value = "nowPage", required = false, defaultValue = "1") int nowPage,
			@RequestParam(value = "search", required = false, defaultValue = "") String searchWord,
			@RequestParam(value = "type", required = false, defaultValue = "fall") String searchType,
			@RequestParam(value = "ftype", required = false, defaultValue = "") String ftype,
			@RequestParam(value = "no") int fno,
			@RequestParam(value = "commPage", required = false, defaultValue = "1") int commPage,
			HttpServletRequest request, ModelAndView mv){
		System.out.println("FreeBoardController.freeBoardDetail" + request.getMethod() + "method");
		
		//비즈니스로직
		FreeBoardDTO fdto=freSVC.detail(fno);//게시글
		ArrayList<FreeBoardDTO> files = freSVC.getFile(fno); //첨부파일목록조회
		//PageUtil pInfo = freSVC.getCommPageInfo(fno, commPage);//댓글 페이징
		//ArrayList<FreeBoardDTO> decomm=freSVC.getCommDetail(pInfo);//댓글
		
		System.out.println("fdto = "+fdto);
		System.out.println("files= "+files);
		mv.addObject("DETAIL",fdto);//게시글
		mv.addObject("FILE",files);//이미지파일
		//mv.addObject("PINFO", pInfo); //페이징 정보
		//mv.addObject("COMM",decomm);//댓글
		
		//뷰 지정
		mv.setViewName("diary/user/freeboard/boardDetail");
		return mv;
	}
}
