package com.naver.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        memberService = new MemberService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 encoding 처리
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//pathinfo
		String command = request.getPathInfo();
		//method
		String method = request.getMethod();
		//forward, redirect 선택
		boolean check=true;
		//url
		String path="";
		
		try {
		if(command.equals("/memberJoin")) {
			if(method.equals("POST")) {
				MemberDTO memberDTO = new MemberDTO();
				System.out.println("member add");
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setUpw(request.getParameter("upw"));
				memberDTO.setUname(request.getParameter("uname"));
				memberDTO.setAge(Integer.parseInt(request.getParameter("age")));
				memberDTO.setEmail(request.getParameter("email"));
				memberDTO.setPhone(Integer.parseInt(request.getParameter("phone")));
				
				int result=memberService.memberAdd(memberDTO);
				
				if(result>0) {
					check=false;
					path="../"; //인덱스 페이지
				}
				
			}else { //GET
				check=true;
				path="../WEB-INF/views/member/memberJoin.jsp";
			}
		}else if(command.equals("/memberLogin")) {//로그인
			if(method.equals("POST")) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setUpw(request.getParameter("upw"));
				
				memberDTO = memberService.memberLogin(memberDTO);//새로 선언하는 것보다 안쓰는  memberDTO를 재사용
				
				if(memberDTO != null) {//로그인 성공
					HttpSession session = request.getSession();
					session.setAttribute("member", memberDTO);//로그인성공시 닫을때까지 유지됨
					check=false;
					path="../";//redirect
				}else {//로그인 실패
					request.setAttribute("result","Login Fail");//alert
					request.setAttribute("path","./memberLogin");//location
					path="../WEB-INF/views/common/result.jsp";
				}
				
			}else {//get
				check=true;
				path="../WEB-INF/views/member/memberLogin.jsp";
			}
		
		}else if(command.equals("/memberLogout")) {//로그아웃
				HttpSession session = request.getSession();
				session.invalidate();//세션의 시간을 강제로 종료시켜버림
				check=false;
				path="../";	
				
		}else if(command.equals("/memberSelectOne")) {
			
		}else if(command.equals("/memberPage")) {//내 정보
			
			path="../WEB-INF/views/member/memberPage.jsp";//forward방식
			
		}else if(command.equals("/memberUpdate")) {
			
		}else if(command.equals("/memberDelete")) {
			
		}else {
			
		}
		//어떤 방식으로 보낼 것인가\
		if(check) {//foward
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}else { //redirect
			response.sendRedirect(path);
		}
		
		}catch (Exception e) {
			// TODO: handle exception
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
