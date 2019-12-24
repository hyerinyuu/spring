package com.biz.memo.service;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.memo.domain.UserDTO;
import com.biz.memo.persistence.UserDao;

/*
 * interface를 implements하여 생성한 클래스에 @Service Annotation을 붙여주면
 * @Autowired를 수행할 때
 * 
 * Interface 객체 형식으로 코드를 작성하면
 * 자동으로 해당 객체를 가져와서 객체를 초기화 하여준다.
 * 
 * interface가 존재하는 객체는 interface로 선언하고 service로 초기화 시켜준다.
 * Service Annotation은 실제 ServiceClass에 붙여주기(interface에서 붙이지 말기)
 */
@Service
public class UserServiceImp implements UserService{

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder; // rootContext bean의 id와 같게 설정하기
	
	UserDao uDao;
	@Autowired
	public void newUserDao() {
		uDao = sqlSession.getMapper(UserDao.class);
	}
	
	@Override
	public int userJoin(UserDTO userDTO) {
		
		// 이미 한명이라도 테이블에 정보가 저장된 상태라면, grade == U
		if(uDao.userCount() > 0) {
			userDTO.setU_grade("U");
			// 아니면 grade == A
		}else {
			userDTO.setU_grade("A");
		}
		
		String cryptText = passwordEncoder.encode(userDTO.getU_password());
		userDTO.setU_password(cryptText);
		return uDao.userInsert(userDTO);
	}

	@Override
	public boolean userIdCheck(String u_id) {

		UserDTO userDTO = uDao.findById(u_id);
		
		/*
		 * 아래 if문과 같은 코드이나 if문에서 거를수 있는 코드는 
		 * else 조건을 생략하는게 더 효율성이 좋은 코드라고 할 수 있다.
		if(userDTO == null) {
			return false;
		}else {
			return true;
		}
		*/
		
		// null검사를 하지 않으면 만약 u_id가 null일경우 nullpointexception이 발생함.
		if(userDTO != null && userDTO.getU_id().equalsIgnoreCase(u_id)) {
			return true;
		}		
		return false;
	}

	@Override
	public boolean userLoginCheck(UserDTO userDTO) {
		
		// inU_id == 입력받은 u_id
		String inU_id = userDTO.getU_id();
		String inU_pass = userDTO.getU_password();
		
		// 암호화를 대비한 코드로 작성
		// id를 매개변수로 보내고 id로 조회를 하여 회원정보를 받기
		UserDTO userRDTO = uDao.findById(inU_id);
		
		// 회원정보가 없으면
		if(userRDTO == null ) {
			return false;
		}
		
		// s_U_id = select로 조회한 id
		String sU_id = userRDTO.getU_id();
		String sU_pass = userRDTO.getU_password();
		
		// 테이블에 저장된 사용자 비번으로 암호회된 문자열이다.
		String cryptU_pass = userRDTO.getU_password();
		
		// 회원아이디는 존재하지만 패스워드가 틀렸을경우
		// if(sU_id.equalsIgnoreCase(inU_id) && sU_pass.equals(inU_pass)) {
		// Bcrypt로 암호화된 문자열은 equal()등의 method로 match되는지 비교가 불가능하고,
		// BCrypt에서 지원하는 matches() method를 사용하여
		// 비번이 맞는지 비교해야한다.
		if(sU_id.equalsIgnoreCase(inU_id) && passwordEncoder.matches(inU_pass, cryptU_pass)) {
			
			/*
			 * ****************************************************
			 * java method에서 객체를 매개변수로 받은 후,
			 * 각 필드 변수들을 개별적으로 변경을 하면
			 * 파라메터로 주입한 원본의 변수값을이 변경된다.
			 * 
			 * 하지만 객체 = 다른객체
			 * 또는 객체 = new 클래스() 형식으로 자체를 변경해버리면
			 * 파라메터로 주입한 원본은 변경이 되지 않는다.
			 * 
			 * 그래서 이 경우 각 필드변수 요소들을 모두 주입해주어야한다.
			 * userDTO = userRDTO;
			 * ( == userDTO.setU_grade(userRDTO.getu_grade))
			 */
			
			return true;
		}else{
			return false;
		}
	}

	@Override
	public UserDTO getUser(String u_id) {
		
		return uDao.findById(u_id);
	}

}
