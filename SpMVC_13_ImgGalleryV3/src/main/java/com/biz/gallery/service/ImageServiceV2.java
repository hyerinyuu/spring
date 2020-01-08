package com.biz.gallery.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;
import com.biz.gallery.repository.ImageDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("imgServiceV2")
public class ImageServiceV2 extends ImageService{

	@Autowired
	public ImageServiceV2(ImageDao imgDao, FileService fService, ImageFileService ifService) {
		super(imgDao, fService, ifService);
	}

	@Override
	public int update(ImageVO imageVO) {

		List<String> fileNames = imageVO.getImg_file_upload_name();
		if(fileNames != null && fileNames.size() > 0) {
			
			// 첫번째 이미지를 대표 이미지로 설정
			imageVO.setImg_file(fileNames.get(0));
			
			List<ImageFilesVO> files = new ArrayList<ImageFilesVO>();
			for(String fileName : fileNames) {
				files.add(ImageFilesVO.builder()
						.img_file_p_code(imageVO.getImg_seq())
						.img_file_origin_name(fileName.substring(36))
						.img_file_upload_name(fileName).build());
			}
			ifService.imageFileInsert(files, imageVO.getImg_seq());
		}
		
		int ret = imgDao.update(imageVO);
		return ret;
	}

	public int deleteByFileSeq(@Param("img_file_seq") String img_file_seq) {
		
		int ret = imgDao.deleteFileSeq(img_file_seq);
		log.debug("서비스 seq : " + img_file_seq);
		
		return ret;
	}


}
