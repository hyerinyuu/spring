package com.biz.gallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.gallery.domain.MemberVO;
import com.biz.gallery.repository.MemberDao;

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

	public int insert(MemberVO memberVO) {
		
		// 입력된 회원정보 중 password를 암호화 수행
		String cryptText = passwordEncoder.encode(memberVO.getU_password());
		// 암호화된 password를 비밀번호 변수에 setting
		memberVO.setU_password(cryptText);
		
		// 현재 가입된 회원 수 확인
		int nCount = memberDao.MemberCount();
		
		// 가입자가 한명도 없으면
		if(nCount < 1) {
			// 첫번째 가입자의 grade를 admin으로 설정
			memberVO.setU_grade("ADMIN");
		} else if(nCount < 4) {
			memberVO.setU_grade("MEM");
		} else {
			memberVO.setU_grade("GUEST");
		}
		return memberDao.insert(memberVO);
	}

	public MemberVO loginCheck(MemberVO memberVO) {
		// 입력받은 id와 password 값 뽑기
		String u_id = memberVO.getU_id();
		String u_password = memberVO.getU_password();
		
		// db에 있는 id와 일치하는지 검사
		MemberVO loginMemberVO = memberDao.findById(u_id);
		
		// 회원가입을 했으면
		if(loginMemberVO != null) {
			// 암호화된 password 값 뽑아내기
			String cryptPassword = loginMemberVO.getU_password();
			// db에 있는 암호화된 password와 입력받은 password가 일치하는지 matches를 이용해 검사(true면 회원가입 된 멤버)
			if(passwordEncoder.matches(u_password, cryptPassword)) {
				return loginMemberVO;
			}
			
		}
		return null;
	}
	
}
