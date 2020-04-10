package com.naver.notice;

import java.util.ArrayList;
public class NoticeSevice {
	private NoticeDAO noticeDAO;
	
	public NoticeSevice() {
		this.noticeDAO = new NoticeDAO();
	}
	
	public ArrayList<NoticeDTO> noticeList() throws Exception{
		return noticeDAO.noticeList(); 
	}
	public NoticeDTO noticeSelect(int num) throws Exception{
		return noticeDAO.noticeSelect(num);
	}
}