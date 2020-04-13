package com.naver.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {
	private String enc;

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		//filter 객체가 소멸될 때 실행 되는 메서드
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//요청이 발생하면 실행
		request.setCharacterEncoding(enc);
		System.out.println("encoding filter in");
		
		chain.doFilter(request, response);//다음 필터 또는 servlet으로 전달
		
		request.setCharacterEncoding("UTF-8");
		System.out.println("encoding filter out");
		//응답이 발생하면 실행(나갈때)
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//filter 객체가 생성 후 초기화 메서드
		String enc = fConfig.getInitParameter("enc");//초기화 파라미터.이 값을 UTF8과 바뀌게//web.xml의 parameter-value의 값을 변경하기위한 초기화
	}

}
