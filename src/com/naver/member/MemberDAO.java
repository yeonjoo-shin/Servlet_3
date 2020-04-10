package com.naver.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.naver.point.PointDTO;
import com.naver.util.DBConnect;

public class MemberDAO {
	//1.Join
	public int memberAdd(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnect.getConnect();
		
		String sql = "insert into member values(?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		//?값 세팅
		st.setString(1, memberDTO.getId());
		st.setString(2,memberDTO.getUpw());
		st.setString(3, memberDTO.getUname());
		st.setInt(4,memberDTO.getAge());
		st.setString(5, memberDTO.getEmail());
		st.setInt(6,memberDTO.getPhone());
		
		System.out.println("save");
		int result = st.executeUpdate();
		System.out.println(result);
		System.out.println("save result");
		st.close();
		con.close();
		
		return result;
	
	}
	
	//2. Login
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnect.getConnect();
		String sql = "select * from member where id=? and upw=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		//?세팅
		st.setString(1, memberDTO.getId());
		st.setString(2,memberDTO.getUpw());
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {//로그인 성공
			memberDTO.setUname(rs.getString("uname"));
			memberDTO.setEmail(rs.getString("email"));
			memberDTO.setPhone(rs.getInt("phone"));
			memberDTO.setAge(rs.getInt("age"));
		}else {//로그인 실패
			memberDTO=null;
			
		}
		
		rs.close();
		st.close();
		con.close();
		
		return memberDTO;
	
		
	}
	//3.Delete
	public int memberDelete(MemberDTO memberDTO)throws Exception{
		int result =0;
		Connection con = DBConnect.getConnect();
		String sql="delete member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		
		result = st.executeUpdate();
		
		st.close();
		con.close();
		return result;
		
	}
	//4.update
	public int memberUpdate(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnect.getConnect();
		String sql = "UPDATE member set id=?,upw=?,uname=?,age=?,email=?,phone=? where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		//정보는 모든 것을 가져와야함
		st.setString(1, memberDTO.getId());
		st.setString(2,memberDTO.getUpw());
		st.setString(3, memberDTO.getUname());
		st.setInt(4,memberDTO.getAge());
		st.setString(5, memberDTO.getEmail());
		st.setInt(6,memberDTO.getPhone());
		st.setString(7,memberDTO.getId());
		
		int result=st.executeUpdate();
		System.out.println(result);
		st.close();
		con.close();
		
		return result;
	}
	
	
	
}
