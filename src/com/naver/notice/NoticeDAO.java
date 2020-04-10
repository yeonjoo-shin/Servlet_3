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
}
