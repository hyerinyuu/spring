package com.biz.gallery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;
import com.biz.gallery.domain.MemberVO;
import com.biz.gallery.repository.ImageDao;
import com.biz.gallery.repository.ImageFilesDao;
import com.biz.gallery.service.FileService;
import com.biz.gallery.service.ImageFileService;
import com.biz.gallery.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/rest")
@Slf4j
@RestController
public class ImgrestController {
	
	private final FileService fService;
	private final ImageFileService ifService;
	private final ImageDao imgDao;
	private final ImageFilesDao ifDao;
	private final MemberService mService;
	
	
	@Autowired
	public ImgrestController(FileService fService, ImageFileService ifService, ImageDao imgDao, ImageFilesDao ifDao, MemberService mService) {
		super();
		this.fService = fService;
		this.ifService = ifService;
		this.imgDao = imgDao;
		this.ifDao = ifDao;
		this.mService = mService;
	}
	
	@RequestMapping(value="/file_up", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String file_up(@RequestParam("file") MultipartFile upfile) {
		
		String upLoadFileName = fService.file_up(upfile);
		
		if(upLoadFileName == null) return "FAIL";
		else return upLoadFileName;
	}
	
	// window open을 사용해서 어쩔수 없이 GET으로 받아야함
	// pathVariable Annotation이름으로 값이 넘어오면 주소로 넘겨라
	/*
	 * [FileDownload]
	 * 1. 단순히 파일을 클릭했을때 링크를 주고 다운로드 하는 방법(전통적)
	 * 	  파일 이름이 서버에 저장된 이름 그대로 다운로드가 됨.(오른쪽버튼 다른이름으로 저장)
	 *    ==> 서버에 대한 정보가 노출되기 쉽다.
	 *    
	 * 2. response에 파일을 실어서 보내고,
	 * 	  web client 입장에서는 Http 프로토콜의 body에 실려오는 데이터를 수신하는 형태(향상된 기능)
	 *    ==> 서버에 저장된 파일이 노출되지 않더라도 파일 다운로드 가능
	 *    이미지 이외의 파일일 경우 감춰진 폴더에 저장해 두었다가
	 *    별도의 다운로드 기능을 구현하여 다운 받을 수 있도록 하는 경우
	 *    (파일 종류에 관계 없이 다운로드 가능)
	 */	  
	
	@RequestMapping(value="/file_down/{img_file_seq}", method=RequestMethod.GET)
	public String file_down(@PathVariable("img_file_seq") String img_file_seq, HttpServletRequest req, HttpServletResponse res) {
	
		// 1. img_file_seq 값으로 다운로드를 수행할 파일 정보를 DB로부터 추출
		ImageFilesVO imgFVO = ifDao.findBySeq(Long.valueOf(img_file_seq));
		
		// 2. 서버에 저장된 파일 이름(UUID + 파일이름) 가져오기
		String downFileName = imgFVO.getImg_file_upload_name();
		
		// 3. 파일 이름과 서버에 저장된 path 정보를 연결하기
		File downFile = fService.file_down(downFileName);
		
		// 파일이 없으면 ajax에게 not found를 return
		if(downFile == null) return "NOT_FOUND";
	
		
		// 실제 업로드 전 원래 이름으로(UUID + 파일이름) 다운로드를 실행할 수 있도록 준비 
		String originName = imgFVO.getImg_file_origin_name();
		if(originName == null || originName.equals("")) {
			originName = "noname.file";
		}
		
		// 지금 나에게 down을 요청한 browser가 누구냐?(Trident는 지금 사용 안하는 옛날 브라우저)
		String browser = req.getHeader("User-Agent");
		
			
			// 인코딩 부분(특별히 이해할 필요 없이 그냥 이렇게 쓰기)
			try {

				if(browser.contains("MSIE") || browser.contains("Chrome") || browser.contains("Trident")) {
					originName = URLEncoder.encode(originName, "UTF-8").replaceAll("\\+", "%20"); // +기호를 빈칸으로 바꿔라
				}else {
					originName = new String(originName.getBytes("UTF-8"),"ISO-8850-1");
							
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				log.debug("파일이름 인코딩 오류 발생");
		}
		
		// response에 파일을 싣기 위한 설정
		// 내가 보내는 데이터 type이 이러하니 bowser야 받을 준비를 해라
		res.setContentType("application/octer-stream");
		res.setHeader("Content-Transfer-Encoding", "binary;");
		
		// 파일을 보낼 때 원래 이름으로 보이도록 만드는 작업
		originName = String.format("attachment;filename=%s", originName);
		res.setHeader("Content-Disposition", originName);
		
		
		try {
			// response의 통로를 열어줌
			OutputStream os = res.getOutputStream();
			// 서버에 저장된 파일 이름을 inputStream으로 읽어서 outputStream으로 보내줌
			FileInputStream fs = new FileInputStream(downFile);
			
			int nCount = 0;
			// 데이터를  512 byte씩 쪼개서 보낼 bype배열(일반적으로 512 or 1024로 보냄)
			byte[] sendData = new byte[512];
			
			while(true) {
				// nCount : 데이터를 몇byte나 읽었는지 세는 변수(0~511개)
				nCount = fs.read(sendData);
				if(nCount == -1) break;
				
				os.write(sendData, 0, nCount);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.debug("다운로드 중 오류 발생");
		}
		return "OK";
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
	
	@RequestMapping(value="/main_image", method=RequestMethod.POST)
	public String main_image(@RequestParam("img_pcode") String img_pcode, @RequestParam("img_file") String img_file) {
		
		ImageVO imageVO = imgDao.findBySeq(img_pcode);
		// 대표이미지 세팅
		imageVO.setImg_file(img_file);
		int ret = imgDao.update(imageVO);
		
		return ret + "";
	}
	
	// rest/member/login
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(MemberVO memberVO, Model model, HttpSession httpSession) {

		// 입력받은 id가 DB에 있는 id값 중 일치하는 값이 있나 확인해보기 
		memberVO = mService.loginCheck(memberVO);
		
		// Service에서 검사받은 memberVO가 null이 아니면 == 회원가입되었으면
		if(memberVO != null) {
			httpSession.setAttribute("MEMBER", memberVO);
			return "LOGIN_SUCCESS";
		// 혹시 로그인이 안되었을 경우 session 제거해줘야함
		}else {
			httpSession.removeAttribute("MEMBER");
			return "LOGIN_FAILED";
		}
			
		
		
	}
	
	

}
