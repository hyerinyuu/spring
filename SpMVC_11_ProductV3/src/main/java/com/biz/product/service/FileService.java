package com.biz.product.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.product.domain.ProFileDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {

	// servlet-context에 지정해놓은 winFilePath를 자동으로 가져와서 씀
	@Autowired
	String winFilePath;
	
	/*
	 * 여러개의 파일들읠 개별파일로 분리하여
	 * fileUp() method에게 보내서 파일을 upload하도록 하고
	 * fileUp()이 생성한 업로드 파일 이름을 return 받아서
	 * List에 추가한 후 파일 이름들 List를 controller로 return함
	 */
	public List<ProFileDTO> filesUp(MultipartHttpServletRequest u_files) {
		
		List<ProFileDTO> upFileList = new ArrayList<ProFileDTO>();
		
		// 만약 개별파일을 업로드 하는 중에 문제가 발생하면
		// controller로 null return해 파일 업로드에 문제가 발생했음을 알리기
		try {
			
			for(MultipartFile file : u_files.getFiles("u_files")) {
				
				// 파일을 업로드하고 파일명 받기
				// upFileName = UUID + originalFileName
				String upFileName = fileUp(file);
				ProFileDTO pf = ProFileDTO.builder().file_origin_name(file.getOriginalFilename())
													.file_upload_name(upFileName).build();
				upFileList.add(pf);
			}
		} catch (Exception e) {
			log.debug("*********Exception : " + e.getMessage());
			return null;
		}
		return upFileList;
	}
	
	/*
	 * 1개의 파일을 서버폴더에 업로드하고,
	 * 변화된 파일명(UUID + originalFileName)을 return
	 */
	public String fileUp(MultipartFile u_file) throws Exception {
		
		// 업로드된 파일 정보에서 파일 이름만 추출
		String originName = u_file.getOriginalFilename();
		
		String upLoadPath = winFilePath;
		// 파일이 업로드 됐으면  
		if(u_file != null) {
			// /files/폴더에 대한 IO정보를 추출
			File dir = new File(upLoadPath);
			// 현재 서버에 /files/라는 폴더가 없으면
			if(!dir.exists()) {
				// 폴더를 생성해라
				dir.mkdirs();
			}
			
			// original 파일이름을 사용하면 해킹 위험이 높기 때문에
			// 파일 이름을 UUID로 설정하여 DB table에 저장하자
			String strUUID = UUID.randomUUID().toString();
			strUUID += originName;
			
			// upLoadPath + originName;
			// /product/files/2019.jpg 라는 이름으로 파일명을 만들고
			// 해당하는 파일에 대한 정보를 객체로 생성하라
			File upLoadFile = new File(upLoadPath, strUUID);
			
			// web에서 upload된 파일(u_file)을 방금 설정한 파일(uploadFile)에 전송하라
			// web -> server로 파일이 복사된다.
			try {
				// u_file을 upLoadFile로 복사하라
				// upLoadFile로 저장하라
				u_file.transferTo(upLoadFile);
				
				// 파일이 정상적으로 upload가 되면
				// originalName을 controller로 return함
				return strUUID;
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// 파일이 정상적으로 upload가 안되면 null값을 Controller로 return하라
		return null;
		
	}

	public void fileDelete(String p_file) {

		// 문자열로 받은 파일을 삭제하기
		File dFile = new File(winFilePath, p_file);
		if(dFile.exists()) {
			boolean ok = dFile.delete();
			
			if(ok) {
				log.debug("파일 삭제 성공");
				
			}else {
				log.debug("파일 삭제 실패");
			}
		}else {
			log.debug("삭제할 파일이 없음");
		}
	}
	
	
}
