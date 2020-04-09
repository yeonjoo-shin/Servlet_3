package com.naver.point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.naver.util.DBConnect;

public class PointDAO {
	//DAO (Data Access Object)
	
	//1. List
	public ArrayList<PointDTO> pointList() throws Exception{
		ArrayList<PointDTO> ar = new ArrayList<PointDTO>();
		
		//1. DB 연결
		Connection con = DBConnect.getConnect();

		//2. SQL문 작성
		String sql ="Select * from point order by num asc";
		
		//3. SQL 미리 전송
		PreparedStatement st = con.prepareStatement(sql);
		
		//4. ? 세팅
		
		//5. 최종 전송 후 결과 처리
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			PointDTO pointDTO = new PointDTO();
			pointDTO.setName(rs.getString("name"));
			pointDTO.setNum(rs.getInt("num"));
			pointDTO.setKor(rs.getInt("kor"));
			pointDTO.setEng(rs.getInt("eng"));
			pointDTO.setMath(rs.getInt("math"));
			pointDTO.setTotal(rs.getInt("total"));
			pointDTO.setAvg(rs.getDouble("avg"));
			ar.add(pointDTO);
		}
		
		//6. 자원 해제
		rs.close();
		st.close();
		con.close();
		
		return ar;
		
	}
	
	//2. SelectOne
	public PointDTO pointSelect(int num) throws Exception{//pointdto하면 한줄씩 빼올수잇음 //pointdto 매개변수로 num 빼 올 수 있음(지금은 int)
		PointDTO pointDTO = null;
		Connection con = DBConnect.getConnect();
		String sql ="SELECT * FROM point WHERE num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,num);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) { //결과가 1개 아니면 없으니까 if 사용
			pointDTO = new PointDTO();
			pointDTO.setName(rs.getString("name"));
			pointDTO.setNum(rs.getInt("num"));
			pointDTO.setKor(rs.getInt("kor"));
			pointDTO.setEng(rs.getInt("eng"));
			pointDTO.setMath(rs.getInt("math"));
			pointDTO.setTotal(rs.getInt("total"));
			pointDTO.setAvg(rs.getDouble("avg"));
		}
		
		rs.close();
		st.close();
		con.close();
		
		return pointDTO;
	}
	//3. Insert
	public int pointAdd(PointDTO pointDTO) throws Exception{
		Connection con = DBConnect.getConnect();
		
		//쿼리문 작성
		String sql = "insert into point values(?,?,?,?,?,?,?)";
		//쿼리문 미리 전송
		PreparedStatement st = con.prepareStatement(sql);
		//? 값 세팅
		st.setString(1,pointDTO.getName());
		st.setInt(2, pointDTO.getNum());
		st.setInt(3, pointDTO.getKor());
		st.setInt(4, pointDTO.getEng());
		st.setInt(5,pointDTO.getMath());
		st.setInt(6,pointDTO.getTotal());
		st.setDouble(7, pointDTO.getAvg());
		System.out.println("save");
		
		int result = st.executeUpdate();//전송 끝
		System.out.println("save result");
		st.close();
		con.close();
		return result;
		
	}
	//4. Update
	public int pointMod(PointDTO pointDTO) throws Exception{
		Connection con = DBConnect.getConnect();
		String sql = "UPDATE point set name=?,num=?,kor=?,eng=?,math=?,total=?,avg=? where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		//정보는 모든 것을 가져와야함
		st.setString(1,pointDTO.getName());
		st.setInt(2, pointDTO.getNum());
		st.setInt(3, pointDTO.getKor());
		st.setInt(4, pointDTO.getEng());
		st.setInt(5,pointDTO.getMath());
		st.setInt(6, pointDTO.getTotal());
		st.setDouble(7, pointDTO.getAvg());
		st.setInt(8, pointDTO.getNum());
	
		
		System.out.println("save");
		
		int result=st.executeUpdate();
		System.out.println("save result");//result 1이면 성공 

		st.close();
		con.close();
		
		return result;
		
	}
	
	
	//5. Delete
	public int pointDelete(int num)throws Exception{
		int result=0;
		Connection con = DBConnect.getConnect();
		String sql="delete point where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		result = st.executeUpdate();
		
		st.close();
		con.close();
		return result;
	}

}
