package com.naver.point;

import java.util.ArrayList;

public class PointService {
	private PointDAO pointDAO;
	//1.List
	
	public PointService() {
		this.pointDAO = new PointDAO();
	}
	//1. list
	public ArrayList<PointDTO> pointList() throws Exception{
		return pointDAO.pointList();
	}
	//2.select
	public PointDTO pointSelect(int num) throws Exception{
		return pointDAO.pointSelect(num);
	}
	//3.delete
	public int pointDelete(int num)throws Exception{
		return pointDAO.pointDelete(num);
	}
}
