package com.biz.gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.service.FileService;
import com.biz.gallery.service.ImageFileService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/rest")
@Slf4j
@RestController
public class ImgrestController {
	
	private final FileService fService;
	private final ImageFileService ifService;

	@Autowired
	public ImgrestController(FileService fService, ImageFileService ifService) {
		super();
		this.fService = fService;
		this.ifService = ifService;
	}

	@RequestMapping(value="/file_up", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String file_up(@RequestParam("file") MultipartFile upfile) {
		
		String upLoadFileName = fService.file_up(upfile);
		
		if(upLoadFileName == null) return "FAIL";
		else return upLoadFileName;
	}
	
	@RequestMapping(value="/imageDelete", method=RequestMethod.POST)
	public String image_delete(@RequestParam("img_id") String img_id) {
		
		try {
			ImageFilesVO imgVO = ifService.findBySeq(img_id); 
			fService.file_delete(imgVO.getImg_file_upload_name());
		} catch (Exception e) {
			return "FAIL";
		}
		// REST Controller라서 문자열 바로 return 가능
		return "OK";
	}
	
}
