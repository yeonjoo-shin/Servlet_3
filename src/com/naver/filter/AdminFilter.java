package com.naver.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.naver.member.MemberDTO;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");//오브젝트 타입이므로 형변환 필요
		
		String command= ((HttpServletRequest)request).getPathInfo();//경로필터를위해
		
		if(memberDTO != null) {//login 성공
			String id= memberDTO.getId();
			if(id.equals("admin2")) {
				//admin 관리자
				System.out.println("Admin");
				chain.doFilter(request, response);//다음필터나 서블릿으로 보내기
			}else {
				//member 일반 사용자
				//리다이렉트로 클라이언트로 돌려보냄
				((HttpServletResponse)response).sendRedirect("../member/memberLogin");//로그인부터 해라
			}
		}else {//로그인 실패
			System.out.println("Member");
			((HttpServletResponse)response).sendRedirect("../member/memberLogin");//로그인부터 해라
			/*request.setAttribute("result", "권한이 필요합니다.");
			request.setAttribute("path", "../");
			
			RequestDispatcher view = request.getRequestDispatcher("../common/result.jsp");//엘럿창 띄우기
			view.forward(request, response);*/
			
			
		}
		
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
