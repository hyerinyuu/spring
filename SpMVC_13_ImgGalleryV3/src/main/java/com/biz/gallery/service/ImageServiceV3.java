package com.biz.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;
import com.biz.gallery.repository.ImageDao;

@Service
public class ImageServiceV3 extends ImageServiceV2{

	@Autowired
	public ImageServiceV3(ImageDao imgDao, FileService fService, ImageFileService ifService) {
		super(imgDao, fService, ifService);
	}
	
	/* 
	 * [접근제한자]
	 * 
	 * public Class c {
	 *   public void method(){
	 *   	public int value = 0;
	 *   }
	 * } 
	 * 
	 * public Class c {  
	 *   private void method(){
	 *   	private int value = 0;
	 *   }
	 * }
	 * 
	 * public : 어떤 클래스나 접근 가능
	 * 			 Class c = new Class();
	 * 			 c.method();
	 * 			 c.value;
	 * 
	 * private : 현재 클래스만 접근 가능
	 * 			 Class c = new Class();
	 * 			 c.method();   // 오류
	 * 			 c.value;	   // 오류	
	 * 
	 * protected : 현재 클래스와 그 클래스를 상속받은 클래스에서만 접근 가능
	 */	 
	
	/*
	 * Service, ServiceV1, ServiceV2에는 없고 serviceV3에만 있는 새로운 method
	 * 현재는 V3에서만 사용되는 method임.
	 * 만약 V3를 상속받아서 새로운 class를 만들경우에는 사용가능함(protected)
	 */
	protected ImageVO updateImageFiles(ImageVO imageVO) {

		List<ImageFilesVO> imgFiles = imageVO.getImg_up_files();
		
		if(imgFiles != null && imgFiles.size() > 0) {
			// 대표파일을 list의 첫번째 파일로 setting
			imageVO.setImg_file(imgFiles.get(0).getImg_file_upload_name());
			for(ImageFilesVO ifVO : imgFiles) {
			    // 파일 오리지날 이름을 업로드이름의 UUID를 제거한 이름으로 setting
				ifVO.setImg_file_origin_name(ifVO.getImg_file_upload_name().substring(36));
			}
		}
		return imageVO;
	}

	@Override
	public int insert(ImageVO imageVO) {

		imageVO = this.updateImageFiles(imageVO);
		
		int ret = imgDao.insert(imageVO);
		long img_pcode = imageVO.getImg_seq();
	
		// bulk insert
		ifService.imageFileInsert(imageVO.getImg_up_files(), img_pcode);
		return ret;
	}

	@Override
	public int update(ImageVO imageVO) {
		
		imageVO = this.updateImageFiles(imageVO);
		int ret = imgDao.update(imageVO);
		long img_pcode = imageVO.getImg_seq();
		ifService.imageFileInsert(imageVO.getImg_up_files(), img_pcode);
		return ret;
	}
	
	
	
	
}
