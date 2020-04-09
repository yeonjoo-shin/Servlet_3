package com.naver.point;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PointController
 */
@WebServlet("/PointController")
public class PointController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PointService pointService; //멤버변수에 객체 생성해주기
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointController() {
        super();
        pointService = new PointService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 encoding 처리
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//pathInfo
		String command = request.getPathInfo();
		
		//Method 형식 //get,post구분
		String method = request.getMethod();
		
		//forward(true), redirect(false) 둘 중 하나선택
		boolean check = true;
		
		//URL path를 담을 변수
		String path ="";
		
		try {
		if(command.equals("/pointList")) {
			
			ArrayList<PointDTO> ar = pointService.pointList();
			request.setAttribute("list",ar);//내장객체에 값을 꺼내올때
			
			path="../WEB-INF/views/point/pointList.jsp";
			
		}else if(command.equals("/pointAdd")) {
			if(method.equals("POST")) {
			
				PointDTO pointDTO = new PointDTO();
				System.out.println("post add");
				pointDTO.setName(request.getParameter("name"));
				pointDTO.setNum(Integer.parseInt(request.getParameter("num")));
				pointDTO.setKor(Integer.parseInt(request.getParameter("kor")));
				pointDTO.setEng(Integer.parseInt(request.getParameter("eng")));
				pointDTO.setMath(Integer.parseInt(request.getParameter("math")));
							
				
				int result=pointService.pointAdd(pointDTO);
				
//				if(result>0) {
//		               check = false;
//		               path = "./pointList";
//		            }else {
//		               check = false;
//		               path = "./pointList";
//		            }
				String msg ="점수 등록 실패";
				
				if(result>0) {
					msg="점수 등록 성공";
				}
				
				request.setAttribute("result",msg);
				request.setAttribute("path","./pointList");
				path = "../WEB-INF/views/common/result.jsp";
				
			}else {//GET
				
				check=true;
				path="../WEB-INF/views/point/pointAdd.jsp";
				
				
			}
		}else if(command.equals("/pointMod")) {
			if(method.equals("POST")) {
				PointDTO pointDTO = new PointDTO();
				
				//수정할 정보들
				pointDTO.setName(request.getParameter("name"));
				pointDTO.setNum(Integer.parseInt(request.getParameter("num")));
				pointDTO.setKor(Integer.parseInt(request.getParameter("kor")));
				pointDTO.setEng(Integer.parseInt(request.getParameter("eng")));
				pointDTO.setMath(Integer.parseInt(request.getParameter("math")));
				
				int result = pointService.pointMod(pointDTO);
				
				
		         String msg="점수 수정 실패";
		        if(result>0) {
		        	msg="점수 수정 성공";
		        	request.setAttribute("path", "./pointSelect?num="+pointDTO.getNum());
		        }else{
		        	request.setAttribute("path","./pointList");
		        	
		        }
		         request.setAttribute("result",msg);
		         path="../WEB-INF/views/common/result.jsp";
			}else {
				check=true;
				int num=Integer.parseInt(request.getParameter("num"));
				PointDTO pointDTO = pointService.pointSelect(num);//선택페이지에서 번호를 기준으로 가져와서 넘기기
				request.setAttribute("dto",pointDTO);
				
				path="../WEB-INF/views/point/pointMod.jsp"; //forwar로 보낼 주소
				
			}
		}else if(command.equals("/pointSelect")) {
			int num=Integer.parseInt(request.getParameter("num"));
			
			PointDTO pointDTO = pointService.pointSelect(num);
			
			request.setAttribute("dto",pointDTO);
			
			path="../WEB-INF/views/point/pointSelect.jsp";
			
		}else if(command.equals("/pointDelete")) {
			int num = Integer.parseInt(request.getParameter("num"));
			
			int result = pointService.pointDelete(num);
			
			check=false;
			path="./pointList";
		}else {

		}
		
		//어떤 방식으로 어디로 보낼건지 체크 forward/redirect
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
