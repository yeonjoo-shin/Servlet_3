package com.naver.member;

public class MemberService {
	private MemberDAO memberDAO;
	
	public MemberService() {
		this.memberDAO = new MemberDAO();
	}
	//join
	public int memberAdd(MemberDTO memberDTO) throws Exception{
		int result = memberDAO.memberAdd(memberDTO);
		return result;
	}
	//login
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception{
		return memberDAO.memberLogin(memberDTO); // 리턴갑은  null or null 아닌값
	}
	//delete
	public int memberDelete(MemberDTO memberDTO) throws Exception{
		return memberDAO.memberDelete(memberDTO);
	}
	//update
	public int memberUpdate(MemberDTO memberDTO) throws Exception{
		return memberDAO.memberUpdate(memberDTO);
	}
}
