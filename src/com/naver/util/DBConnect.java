package com.naver.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	//test를 위한 main
	public static void main(String[] args) {
		try {
			Connection con = DBConnect.getConnect();
			System.out.println(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnect() throws Exception {
		//1. 로그인 정보 4개 기술
		String user = "user02";
		String password = "user02";
		String url = "jdbc:oracle:thin:@192.168.56.102:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		//2. 드라이버를 메모리에 로딩
		Class.forName(driver);
		//3. 로그인 Connection 객체 반환
		Connection con = DriverManager.getConnection(url, user, password);
		
		return con;
	}
}
