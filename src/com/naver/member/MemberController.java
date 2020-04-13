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
		
		getServletConfig();//현재 서블릿의 객체 저장
		getServletContext();//전체 정보 저장 객체(application, context)
		
		getServletConfig().getInitParameter("");//현재 서블릿 객체 파라미터 가져오기
		getServletContext().getInitParameter("");//전체 정보 파라미터
		
		
		//pathinfo
		String command = request.getPathInfo();
		//method
		String method = request.getMethod();
		//forward, redirect 선택
		boolean check=true;
		//url
		String path="";
		
		try {
		if(command.equals("/memberJoin")) {//회원가입
			if(method.equals("POST")) {
				MemberDTO memberDTO = new MemberDTO();

				//dto에 해당 정보를 담아서 가져올꺼야
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setUpw(request.getParameter("upw"));
				memberDTO.setUname(request.getParameter("uname"));
				memberDTO.setAge(Integer.parseInt(request.getParameter("age")));
				memberDTO.setEmail(request.getParameter("email"));
				memberDTO.setPhone(Integer.parseInt(request.getParameter("phone")));
				
				int result=memberService.memberAdd(memberDTO);//db에 갔다가 다시 받아올거야
				
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
					HttpSession session = request.getSession();//로그인 정보를 담고 있기위해   session에 담기
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
			if(method.equals("POST")) {
				HttpSession session = request.getSession();//세션에 정보를 담을거야
				
				MemberDTO memberDTO =new MemberDTO(); //객체생성해서
				//이 객체에 수정된 정보들을 담을거야
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setUpw(request.getParameter("upw"));
				memberDTO.setUname(request.getParameter("uname"));
				memberDTO.setAge(Integer.parseInt(request.getParameter("age")));
				memberDTO.setEmail(request.getParameter("email"));
				memberDTO.setPhone(Integer.parseInt(request.getParameter("phone")));
				
				memberService.memberUpdate(memberDTO);//memberdto를 업데이트에 매개변수로 보내줌 .받아서 
				
				session.setAttribute("member", memberDTO);//수정된값들을 덮어덮어
				
				check = false;
				path="../";
				
			}else {//get
				
				check=true;
				path="../WEB-INF/views/member/memberUpdate.jsp";//어느 페이지에서 업데이트수정할지 보이는 페이지
			}
			
			
		}else if(command.equals("/memberDelete")) {
			
			HttpSession session=request.getSession();//로그아웃할때 까지 유지할거야
			MemberDTO memberDTO =(MemberDTO) session.getAttribute("member");//로그인한 사람의 정보를 가져옴
			int result=memberService.memberDelete(memberDTO);//dto에 담아서 db에서 확인해서 받아와
			
			check=false;
			path="./memberLogout";
			
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
