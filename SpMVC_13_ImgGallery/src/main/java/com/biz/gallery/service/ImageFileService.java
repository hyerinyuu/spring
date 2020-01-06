package com.biz.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.repository.ImageFilesDao;

@Service
public class ImageFileService {

	ImageFilesDao ifDao;
	
	@Autowired
	public ImageFileService(ImageFilesDao ifDao) {
		
		this.ifDao = ifDao;
	}
	
	public int imageFileInsert(List<ImageFilesVO> img_files) {
		
		int ret = 0;
		
		// sql문을 annotation으로 호출하는 방식은 편리하지만
		// forEach를 이용해 멀티파일을 업로드 하기 좀 애매함
		// 그래서 for문으로 file개수만큼 insert하는 방식을 주로 사용
		for(ImageFilesVO file : img_files) {
			ret += ifDao.insert(file);
		}
		return ret;
	}
	
	
	
}
