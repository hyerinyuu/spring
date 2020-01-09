package com.biz.gallery.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;
import com.biz.gallery.domain.MemberVO;
import com.biz.gallery.service.ImageServiceV3;

import lombok.extern.slf4j.Slf4j;

@SessionAttributes({"imageVO"})
@Slf4j
@RequestMapping(value="/image")
@Controller
public class ImgController {

	// interface가 아니고 그냥 class여서 qualified 안붙임
	ImageServiceV3 imService;
	
	@Autowired
	public ImgController(ImageServiceV3 imService) {
		this.imService = imService;
	}
	
	/*
	 * session에 값이 없으면 modelAttribute가 작동해서 bean을 새로 생성해주지만
	 * session에 값이 있으면 그냥 그 값을 가지고 넘어감
	 * 그래서 update를 수행할 때 이전 값이 남아있는 경우가 있는데
	 * 깔끔한 해결법은 아직까지 존재하지 않고
	 * SessionStatus setComplete를 사용하고
	 * upload method에 imageVO를 다시 새로 생성해줘서 값을 초기화 해주는 식으로 
	 * 우회해야한다.
	 */
	@ModelAttribute("imageVO")
	public ImageVO newImageVO() {
		
		return new ImageVO();
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String List(Model model) {
		
		List<ImageVO> imageList = imService.selectAll();
		model.addAttribute("imgList", imageList);
		return "home";
	}
	
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String upload(@ModelAttribute("imageVO") ImageVO imageVO, Model model, HttpSession httpSession) {
		
		log.debug("이미지 업로드 start");
		/*
		// httpSession에 Member라는 attribute를 꺼내서 MemberVO에 담겠음
		MemberVO member = (MemberVO) httpSession.getAttribute("MEMBER");
		if(member == null) {
			
			model.addAttribute("MODAL", "LOGIN");
			return "home";
		}
		*/
		
		imageVO = new ImageVO();
		
		model.addAttribute("BODY", "UPLOAD");
		model.addAttribute("imageVO", imageVO);
		return "home";
	}
	
	// controller에서 업로드된 파일 이름들을 매개변수로 받아도 되지만
	// 그럼 controller에서 일이 많아지기 때문에
	// imageVO에 List<String> img_files를 만들어서 여기서 불러서 쓺
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@ModelAttribute("imageVO") ImageVO imageVO, SessionStatus status) {
		
//		for(String f : imageVO.getImg_files()) {
//			log.debug("파일이름 : " + f);
//		}
//		
		log.debug("업로드 : " + imageVO.toString());
		
	 	imService.insert(imageVO);
		status.setComplete();
		return "redirect:/image/list";
	}
	
	/*
	 * @RequestParam : ?변수=값  형식으로 전송하고 변수에서 수신
	 * @PathVariable : path/값   형식으로 전송하고 변수에서 받기
	 * ==> 변수 이름을 노출하지 않아도 됨(일종의 RestController와 비슷)
	 * 근래에는 ?변수=값 보다 이방식을 더 많이 사용
	 */
	@RequestMapping(value="/update/{img_seq}", method=RequestMethod.GET)
	public String update(@PathVariable("img_seq") String img_seq, Model model, HttpSession httpSession) {
		
		/*
		// 단순히 로그인이 되었는지만 검사할거라서 OBJECT로 세션 객체를 추출
		Object memberVO = httpSession.getAttribute("MEMBER");
		
		// 로그인이 안되었으면
		if(memberVO == null){
			model.addAttribute("MODAL", "LOGIN");
			return "home";
		}
		*/
		
		ImageVO imgVO = imService.findBySeq(img_seq);
		
		model.addAttribute("BODY", "UPLOAD");
		model.addAttribute("imageVO", imgVO);
		
		return "home";
	}
	
	@RequestMapping(value="/update/{img_seq}", method=RequestMethod.POST)
	public String update(@ModelAttribute("imageVO")ImageVO imageVO, SessionStatus status) {
		
		
		log.debug("####IMAGEVO : " + imageVO.getImg_files());
		
		// 이미지의 이름이 기존의 이미지와 다르면 기존의 이미지를 삭제해야함
		// 서비스에 해당 기능 구현 
		int ret = imService.update(imageVO);
		
		/*
		 * sessionAttributes를 사용할 때
		 * 객체가 서버 메모리에 남아서 계속 유지되는 상태.
		 * insert, update 등을 수행할 때 그 정보를 계속 사용해서 form에 값이 나타나게 된다.
		 * 
		 * 이런 현상을 방지하기 위해서
		 * insert, update가 완료된 후에는 반드시
		 * SessionStatus의 setComplete() method를 호출해서 sessionAttribute를 해제 해주어야 한다.
		 * 매개변수에 등록해야함(매개변수임 == dispatcher와 통신)
		 */
		status.setComplete();
		
		return "redirect:/image/list";
	}
	
	@RequestMapping(value="/delete/{img_seq}", method=RequestMethod.GET)
	public String delete(@PathVariable String img_seq, SessionStatus status, Model model, HttpSession httpSession) {

		// 단순히 로그인이 되었는지만 검사할거라서 OBJECT로 세션 객체를 추출
		Object memberVO = httpSession.getAttribute("MEMBER");
		
		/*
		// 로그인이 안되었으면
		if(memberVO == null){
			model.addAttribute("MODAL", "LOGIN");
			return "home";
		}
		*/
		
		int ret = imService.delete(img_seq);
		
		status.setComplete();
		return "redirect:/image/list";
	}
	
	
	// model은 필요없는데 delete GET과 충돌해서 그냥 임의로 넣음
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam("img_seq") String img_seq, SessionStatus status, Model model) {
		
		int ret = imService.delete(img_seq);
		
		status.setComplete();
		return ret + "";
	}
	
	@ResponseBody
	@RequestMapping(value="/deletefileseq", method=RequestMethod.POST)
	public String deleteFileSeq(@RequestParam("img_file_seq") String img_file_seq, SessionStatus status) {
		
		int ret = imService.deleteByFileSeq(img_file_seq);
		log.debug("컨트롤러 img_file_seq : " + img_file_seq);
		status.setComplete();
		return ret + "";
	}
	
	// [MultipartHttpServletRequest mFiles] : 다중 파일 수신하기
	// 파일 리스트를 view와 결합하여 저장 전 보여주기
	@RequestMapping(value="/files_up", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
		// files == home.jsp에 append에 설정한 이름
		public String files_up(MultipartHttpServletRequest mFiles, Model model) {
		
			List<ImageFilesVO> fileNames = imService.files_up(mFiles);
			
			model.addAttribute("imgList", fileNames);
			return "include/img_upload_list";
	}
	
	
}
