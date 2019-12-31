package com.biz.todo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.todo.domain.ToDoList;
import com.biz.todo.repository.ToDoListDao;

@Service("toServiceV1")
public class ToDoServiceV1 implements ToDoService{
	
	/*
	 * SqlSession을 직접 autowired로 연결해주지 않고
	 * mybatis-context.xml에 Dao를 bean으로 생성해주고(interface는 bean으로 생성하기 까다로워서 이렇게 생성해야함) 
	 * SqlSessionFactory와 연결하면 자동으로 class를 생성해줌
	 * 
	 * ##################################
	 * mybatis-context.xml에 mapperFactoryBean bean을 설정해두면
	 */
	@Autowired
	protected ToDoListDao toDao;
	
	@Override
	public List<ToDoList> selectAll() {
		return toDao.selectAll();
	}

	@Override
	public int insert(ToDoList todoList) {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("HH:MM:ss");
		
		String curDate = sd.format(date);  // 문자열형 날짜 생성
		String curTime = st.format(date);  // 문자열형 시간 생성
		
		String strTdComp = todoList.getTdComplete();
		if(strTdComp == null || strTdComp.isEmpty()) {
			todoList.setTdComplete("N");
		}
		
		String strTdAlarm = todoList.getTdAlarm();
		if(strTdAlarm == null || strTdAlarm.isEmpty()) {
			todoList.setTdAlarm("N");
		}
		
		todoList.setTdDate(curDate);
		todoList.setTdTime(curTime);
		int ret = toDao.insert(todoList);
		
		return ret;
	}

	@Override
	public ToDoList findBySeq(long tdSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ToDoList> findBySubject(String tdSubject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ToDoList todoList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long tdSeq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int complete(String strSeq, String tdComplete) {
		return 0;
	}

	@Override
	public int alarm(String strSeq, String tdAlarm) {
		return 0;
	}

}
