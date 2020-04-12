package com.naver.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.naver.member.MemberDTO;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeSevice noticeSevice;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeController() {
        super();
        noticeSevice = new NoticeSevice();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//경로
		String command = request.getPathInfo();
		System.out.println(command);
		//메서드 형식
		String method = request.getMethod();
		//foward / redirect
		boolean check=true;
		//url path
		String path="";
		
		
		
		try {
		if(command.equals("/noticeList")) {
			ArrayList<NoticeDTO> ar = noticeSevice.noticeList();
			request.setAttribute("list", ar);
			
			path="../WEB-INF/views/notice/noticeList.jsp";
		}else if(command.equals("/noticeSelect")) {
			
			int num = Integer.parseInt(request.getParameter("num"));
			NoticeDTO noticeDTO = noticeSevice.noticeSelect(num);
			
			HttpSession session = request.getSession();
			session.setAttribute("dto",noticeDTO);
			
			path="../WEB-INF/views/notice/noticeSelect.jsp";
			
		}else if(command.equals("/noticeAdd")) {
			if(method.equals("POST")) {
				HttpSession session = request.getSession();
				
				MemberDTO memberDTO = new MemberDTO();
				memberDTO = (MemberDTO) session.getAttribute("member");
				
				NoticeDTO noticeDTO = new NoticeDTO();
				
				noticeDTO.setTitle(request.getParameter("title"));
				noticeDTO.setName(request.getParameter("name"));
				noticeDTO.setContent(request.getParameter("content"));
				
				int result = noticeSevice.noticeAdd(noticeDTO);
			
				if(result>0) {
					check=false;
					path="./noticeList";
				}
					
			}else {//get
				check=true;
				path="../WEB-INF/views/notice/noticeAdd.jsp";
			}
		}else if(command.equals("/noticeDelete")) {
			int num=Integer.parseInt(request.getParameter("num"));
			int result = noticeSevice.noticeDelete(num);
			
			check=false;
			path="./noticeList";
			
		}else if(command.equals("/noticeMod")) {
			if(method.equals("POST")) {
				NoticeDTO noticeDTO = new NoticeDTO();
				HttpSession session = request.getSession();
				noticeDTO = (NoticeDTO) session.getAttribute("dto");

				//수정할 정보들
				noticeDTO.setTitle(request.getParameter("title"));
				noticeDTO.setContent(request.getParameter("content"));
		
				System.out.println(noticeDTO.getTitle());
				System.out.println(noticeDTO.getContent());
				System.out.println(noticeDTO.getNum());
				
				int result=noticeSevice.noticeMod(noticeDTO);

				String msg="수정 실패";
				if(result>0) {
					msg="수정 성공";
					request.setAttribute("path","./noticeSelect?num="+noticeDTO.getNum());
				}else {
					request.setAttribute("path", "./noticeList");
				}
				request.setAttribute("result", msg);
				path="../WEB-INF/views/common/result.jsp";
				
			}else {//get
			
				int num=Integer.parseInt(request.getParameter("num"));
				NoticeDTO noticeDTO = noticeSevice.noticeSelect(num);//상세페이지에서 번호를 기준으로 가져와서 넘김
				request.setAttribute("dto",noticeDTO);
				path="../WEB-INF/views/notice/noticeMod.jsp";

			}
		}
		
		else {
			
		}
		
		//어떤 방식으로 보낼 것인가 forward / redirect
		if(check) {
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}else {
			response.sendRedirect(path);
		}
		
		}catch (Exception e) {
		// TODO: handle exception
			e.printStackTrace();
	}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
