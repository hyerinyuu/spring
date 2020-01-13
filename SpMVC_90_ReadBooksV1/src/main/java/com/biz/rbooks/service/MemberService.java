package com.biz.rbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.MemberDTO;
import com.biz.rbooks.repository.MemberDao;

@Service
public class MemberService {

	private final MemberDao memberDao;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public MemberService(MemberDao memberDao, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.memberDao = memberDao;
		this.passwordEncoder = passwordEncoder;
	}

	public int insert(MemberDTO memberDTO) {
		
		// 입력된 회원정보 중 password 암호화를 위해 password 가져오기
		String cryptText = passwordEncoder.encode(memberDTO.getM_password());
		// 암호화된 값으로 password setting
		memberDTO.setM_password(cryptText);
		
		return memberDao.insert(memberDTO);
	}

	// 로그인했는지 확인하기 위한 method
	public MemberDTO logincheck(String u_id, String u_password) {
		
		// DB와 일치하는 아이디가 있는지 검사
		MemberDTO loginMemberDTO = memberDao.findById(u_id);
		
		// 가입된 회원이면
		if(loginMemberDTO != null) {
			// 암호화된 비밀번호와 비교하기 위해서 암호화된 비밀번호 가져오기
			String cryptPassword = loginMemberDTO.getM_password();
			if(passwordEncoder.matches(u_password, cryptPassword)) {
				return loginMemberDTO;
			}
			
		}
		
		return null;
	}
	
	
	
}
