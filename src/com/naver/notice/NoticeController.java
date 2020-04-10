package com.naver.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			request.setAttribute("dto",noticeDTO);
			path="../WEB-INF/views/notice/noticeSelect.jsp";
			
		}else {
			
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
