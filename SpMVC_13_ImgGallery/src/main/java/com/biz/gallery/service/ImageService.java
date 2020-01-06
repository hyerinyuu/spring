package com.biz.gallery.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;
import com.biz.gallery.repository.ImageDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageService {

	/*
	 * 기존 객체 생성 방식 : setter 주입 방식
	 * 		@Autowired 
	 * 		클래스 객체
	 */
	
	/* 서비스 내에서만 사용하니까 private로 접근 제한 */
	private final ImageDao imgDao;
	private final FileService fService;
	private final ImageFileService ifService;
	
	/*
	 * 생성자에서 객체 주입
	 * 사용하는 객체를 final로 선언해서 보호할 수 있고,
	 * 혹시 모를 해킹에 의한 데이터 변조를 막을 수 있다.
	 * 클래스의 교차참조를 컴파일러 차원에서 방지할 수 있다.
	 * 	코드가 다소 번잡스러울 수 있지만 
	 * 	(변수가 몇개 없더라도)setter 주입보다는 constructor(생성자) 주입방식을 사용하자
	 * 
	 *  인텔리제이(inteli j) setter 주입방식을 사용하면 ide가 심각한 경고를 보인다.
	 */
	@Autowired
	public ImageService(ImageDao imgDao, FileService fService, ImageFileService ifService) {
		super();
		this.imgDao = imgDao;
		this.fService = fService;
		this.ifService = ifService;
	}
	
	public List<ImageVO> selectAll(){
		
		return imgDao.selectAll();
	}
	
	/*
	 * 일반적으로 dao.insert(VO)를 호출했을때
	 * VO에 담아서 전달한 값은 insert()가 수행된 후에 볼 수 있으나,
	 * seq처럼 SQL에서 생성된 값은 확인이 불가능하다.(title은 볼 수 있으나 seq는 불가능)
	 * 
	 * 그런데 이 값을 insert()후에 사용해야할 경우가 있다.
	 * 
	 * 이때는 dao, mapper SelectKey를 사용해서 값을 생성하면
	 * insert()후에 그 값을 확인/사용 할 수 있다.
	 *  
	 */
	public int insert(ImageVO imageVO) {
		
		List<String> fileList = imageVO.getImg_files();
		
		// 파일을 업로드하지 않으면 Null Point Exception이 나므로 조건 추가
		if(fileList != null && fileList.size() > 0 ) {
			// 이미지가 여러개면 0번째 위치의 파일을 대표 파일로 업로드
			imageVO.setImg_file(imageVO.getImg_files().get(0));
		}
		
		// 1. tbl_gallery에 데이터 insert
		int ret = imgDao.insert(imageVO);

		// 2. 파일 이름들을
		// ImageFilesVO의 리스트에 생성
		// ImageFilesVO에 img_file_p_code 칼럼에
		// tbl_gallery의 seq값을 추가해서 리스트 생성
		List<ImageFilesVO> files = new ArrayList<ImageFilesVO>();
		
		if(fileList != null) {
			for(String fileName : fileList) {
				files.add(ImageFilesVO.builder()
				.img_file_upload_name(fileName)
				.img_file_p_code(imageVO.getImg_seq()).build());
			}
			// 3. 파일정보를 tbl_images에 insert
			ifService.imageFileInsert(files);
		}
		log.debug(imageVO.toString());
		return ret;
	}

	public ImageVO findBySeq(String img_seq) {

		ImageVO imgVO = imgDao.findBySeq(img_seq);
		
		return imgVO;
	}

	/*
	 * 혹시 파일이 변경이 되면 기존 파일을 먼저 제거하고
	 * 새로운 파일로 등록
	 */
	public int update(ImageVO imageVO) {
		
		ImageVO oldImageVO = imgDao.findBySeq(imageVO.getImg_seq() + "");
		
		if(oldImageVO.getImg_file() != null & imageVO.getImg_file() != null) {
			// 새로 업데이트 된 파일 이름이 기존 테이블에 저장된 파일 이름과 다르면 파일 delete를 수행
			if(!oldImageVO.getImg_file().equals(imageVO.getImg_file())) {
				fService.file_delete(oldImageVO.getImg_file());
			}
		}
				
		int ret = imgDao.update(imageVO);
		return ret;
	}

	public int delete(String img_seq) {

		// 게시글을 지우기 전에 파일부터 삭제를 해야함
		ImageVO imgVO = imgDao.findBySeq(img_seq);
		if(imgVO.getImg_file() != null) {
			fService.file_delete(imgVO.getImg_file());
		}
		int ret = imgDao.delete(img_seq);
		return ret;
	}
	
	
	public List<String> files_up(MultipartHttpServletRequest mFiles){
		
		List<String> fileNames = new ArrayList<String>();
		for(MultipartFile file : mFiles.getFiles("files")) {
			
			// 1개의 파일을 업로드 하고 저장한 파일 이름을 return
			// return된 저장파일 이름을 list에 추가
			fileNames.add(fService.file_up(file));
		}
		return fileNames;
	}
}
