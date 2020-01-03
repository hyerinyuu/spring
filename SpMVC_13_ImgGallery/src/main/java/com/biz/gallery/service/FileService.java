package com.biz.gallery.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	// servlet-context.xml에 설정한 filePath		
	@Autowired
	String filePath;
	
	public String file_up(MultipartFile mFile) {
		
		if(mFile == null) {
			return null;
		}
		
		// 폴더 객체 생성
		File dir = new File(filePath);
		if(!dir.exists()) {
			// c:/bizwork/files
			// bizwork 폴더가 있고, files 폴더만 없을때 ==> dir.mkdir();
			
			// bizwork폴더도 없고, files 폴더를 찾을 수 없을때
			// 모든 경로를 생성해줌(호환성면에서 더 좋음)
			dir.mkdirs();
		}
		
		// 범우주적으로 유일한 랜덤 값
		String strUUID = UUID.randomUUID().toString();
		
		String originalName = mFile.getOriginalFilename();
		
		String upLoadFileName = strUUID + originalName;
		
		// 업로드할 파일 객체 생성
		File uploadFile = new File(filePath, upLoadFileName);
		
		try {
			
			mFile.transferTo(uploadFile);
			return upLoadFileName;
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
