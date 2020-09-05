package com.sp.member;

import java.util.Map;

public interface MemberService {

	public int insertMember(Member dto) throws Exception;
	public int login(Member dto) throws Exception;
	public Member readMember(String id) throws Exception;
	public int updateLogindate(String id)  throws Exception;
	public int logout();
	
	public int updateMember(Member dto);
	public int deleteMember(Map<String, Object> map);
	
}
