package com.sp.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("member.memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private CommonDAO dao;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	
	@Override
	public int insertMember(Member dto) throws Exception {
		int result = 0;
		
		try {
			// 암호화
			dto.setPwd(bcryptEncoder.encode(dto.getPwd()));
			
			dao.insertData("member.insertMember", dto);
			result = dao.insertData("member.insertMemberAuthority", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

	@Override
	public int login(Member dto) throws Exception {
		int result =0;
		
		try {
			result = dao.selectOne("member.login", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

	@Override
	public Member readMember(String id) throws Exception {
		Member dto = null;
		
		dto = dao.selectOne("member.readMember", id);
		
		return dto;
	}

	@Override
	public int updateLogindate(String id) throws Exception {
		int result =0;
	
		result = dao.updateData("member.updateLogindate", id);
		
		return result;
	}

	@Override
	public int updateMember(Member dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logout() {
		// TODO Auto-generated method stub
		return 0;
	}

}
