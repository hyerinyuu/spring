package com.biz.todo.service;

import org.springframework.stereotype.Service;

@Service("toServiceV2")
public class ToDoserviceV2 extends ToDoServiceV1 {
	
	@Override
	public int complete(String strSeq, String tdComplete) {
		
		tdComplete = tdComplete.equalsIgnoreCase("Y") ? "N" : "Y";
		long tdSeq = Long.valueOf(strSeq);
		return toDao.complete(tdSeq, tdComplete);
	}

	@Override
	public int alarm(String strSeq, String tdAlarm) {
		
		tdAlarm = tdAlarm.equalsIgnoreCase("Y") ? "N" : "Y";
		long tdSeq = Long.valueOf(strSeq);
		return toDao.alarm(tdSeq, tdAlarm);
	
	}

	
	
	
}
