package com.naver.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.naver.util.DBConnect;

public class NoticeDAO {
	public ArrayList<NoticeDTO> noticeList() throws Exception{
		ArrayList<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		
		Connection con = DBConnect.getConnect();
		String sql="Select * from notice order by num asc";
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setName(rs.getString("name"));
			noticeDTO.setDdate(rs.getDate("ddate"));
			noticeDTO.setHit(rs.getInt("hit"));
			noticeDTO.setContent(rs.getString("content"));
			
			ar.add(noticeDTO);
		}
		
		rs.close();
		st.close();
		con.close();
		
		return ar;
	}
	
	//selectone
	public NoticeDTO noticeSelect(int num) throws Exception{
		NoticeDTO noticeDTO =null;
		Connection con = DBConnect.getConnect();
		String sql ="select * from notice where num=?";
		PreparedStatement st =  con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
		    noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setName(rs.getString("name"));
			noticeDTO.setDdate(rs.getDate("ddate"));
			noticeDTO.setHit(rs.getInt("hit"));
			noticeDTO.setContent(rs.getString("content"));
		}
		
		rs.close();
		st.close();
		con.close();
		
		return noticeDTO;
		
	}
	//Add
	public int noticeAdd(NoticeDTO noticeDTO) throws Exception{
		Connection con = DBConnect.getConnect();
		
		//쿼리문 작성
		String sql = "insert into notice values(SEQ_NUM.nextval,?,?,sysdate,'0',?)";
		//쿼리문 미리 전송
		PreparedStatement st = con.prepareStatement(sql);
		//? 값 세팅
		st.setString(1, noticeDTO.getTitle());
		st.setString(2, noticeDTO.getName());
		st.setString(3, noticeDTO.getContent());
		
		int result = st.executeUpdate();//전송 끝
		st.close();
		con.close();
		return result;
		
	}
	
	//delete
	public int noticeDelete(int num) throws Exception{
		Connection con = DBConnect.getConnect();
		String sql="delete notice where num= ?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		return result;
	}
	
	//Mod
	public int noticeMod(NoticeDTO noticeDTO) throws Exception{
		Connection con = DBConnect.getConnect();
		String sql ="update notice set title=?,content=? where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		
	
		st.setString(1, noticeDTO.getTitle());
		st.setString(2, noticeDTO.getContent());
		st.setInt(3, noticeDTO.getNum());
		
		int result = st.executeUpdate();
		System.out.println("result"+result);

		
		st.close();
		con.close();
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
