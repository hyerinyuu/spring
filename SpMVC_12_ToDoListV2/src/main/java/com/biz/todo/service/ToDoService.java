package com.biz.todo.service;

import java.util.List;

import com.biz.todo.domain.ToDoList;

/*
 * interface의 목적
 * 1. 어떤 Service를 구현할 것인가의 설계도 역할
 * 2. 여러 팀원이 분담하여 프로젝트를 구현할 경우, 
 * 	 꼭 필요한 method를 빼먹지 않고 구현할 수 있도록 하는 guide 역할
 * 3. 자바의 다형성 원리를 이요하여, 버전업(UpGrade)를 할 때,
 *   기존에 사용하던 코드를 최소한으로 변경하여 효과를 낼 수 있도록 하는 목적
 * 4. 클래스를 상속 받을 때 코드가 난해해 지는 것을 방지
 * 5. 클래스와 클래스 사이에서 서로 연관도를 낮추어 유연한 관계를 설정
 * 6. interface는 기획자, PM 등이 미리 설정을 해 두어야 한다.(실무에서는 적용이 힘들어서 거의 이루어지지 않음)
 */
public interface ToDoService {

	public List<ToDoList> selectAll();
	public ToDoList findBySeq(long tdSeq);
	public List<ToDoList> findBySubject(String tdSubject);
	
	public int insert(ToDoList todoList);
	public int update(ToDoList todoList);
	public int delete(long tdSeq);
	public int complete(String strSeq, String tdComplete);
	public int alarm(String strSeq, String tdAlarm);
	
	
	
}
