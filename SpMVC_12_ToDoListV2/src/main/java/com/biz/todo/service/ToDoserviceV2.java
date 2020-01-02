package com.biz.todo.service;

import org.springframework.stereotype.Service;

import com.biz.todo.domain.ToDoList;

@Service("toServiceV2")
public class ToDoserviceV2 extends ToDoServiceV1 {
	
	@Override
	public int complete(String strSeq, String tdComplete) {
		
		tdComplete = tdComplete.equalsIgnoreCase("Y") ? "N" : "Y";
		long tdSeq = Long.valueOf(strSeq);
		return toDao.complete(tdSeq);
	}

	@Override
	public int alarm(String strSeq, String tdAlarm) {
		
		tdAlarm = tdAlarm.equalsIgnoreCase("Y") ? "N" : "Y";
		long tdSeq = Long.valueOf(strSeq);
		return toDao.alarm(tdSeq);
	
	}

	@Override
	public int update(ToDoList todoList) {
		
		return toDao.update(todoList);
	}

	@Override
	public int delete(long tdSeq) {
		return toDao.delete(tdSeq);
	}
	
	
}
