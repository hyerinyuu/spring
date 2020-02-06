package com.biz.crawl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.biz.crawl.domain.NaverMovieVO;
import com.biz.crawl.persistence.NaverDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class MovieCrawlService {
	
	/*
	 * 네이버 현재 상영작 리스트에서
	 * 영화 제목, 이미지, 순위를 가져오기 위해서
	 * url, title이 들어가 있는 tag,
	 * 		image가 들어있는 tag,
	 * 		rankList를 가져오기 위한 tag 묶음 정보 등을 확인
	 */
	// 회원가입 등이 필요 없어 따로 암호화를 하지 않고 단순평문으로 둠.
	private final String naver_movie_url = "https://movie.naver.com/movie/running/current.nhn";
	// movie title
	private final String mTitleTag = "dt.tit a";
	// movie Image
	private final String mImageTag = "div.thumb a img";
	// movie List
	private final String rankListTag = "dl.lst_dsc";
	private final NaverDao nDao;

	public List<NaverMovieVO> selectAll(){
		return nDao.selectAll();
	}
	
	/*
	 * 
	 */
	@Scheduled(fixedDelay = 100000)
	public void naverMovieGet() {
		List<NaverMovieVO> naverList = this.movieRankGet();
		nDao.deleteAll();
		for(NaverMovieVO vo : naverList) {
			nDao.insert(vo);
		}
	}
	
	public List<NaverMovieVO> movieRankGet() {
	
		// 1. URL에 해당하는 html 페이지 코드를 가져오기
		// 2. Document라는 Class에 담기
		// 3. jsoup의 document 클래스를 사용해 dom형식의 document 만들기
		
		
		// jsoup의 Document 클래스를 사용하여 Dom형식의 Document 선언
		Document naverMovieDoc = null;
		
		try {
			naverMovieDoc = Jsoup.connect(naver_movie_url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// DOM에서 rankListTag 문자열을 기준으로 리스트 추출
		Elements rankList = naverMovieDoc.select(rankListTag);
		// DOM에서 imageTag 문자열을 기준으로 리스트 추출
		Elements imgList = naverMovieDoc.select(mImageTag);
		// DOM에서 titleListTag 문자열을 기준으로 리스트 추출
		Elements titleList = naverMovieDoc.select(mTitleTag);
		
		// 자바 1.7이상에서는 ArrayList뒤에 나오는 generic(<NaverMovieCO>)을 생략해도 상관없다.
		List<NaverMovieVO> naverList = new ArrayList<NaverMovieVO>();
		// rankList box들 중에 상위 1부터 10번까지만 가져오기 수행
		for(int i = 0; i < 10; i++) {
			
			// dt.tit a에 담긴 text를 추출하기 == 영화제목
			String title = titleList.get(i).text();
			
			// href 속성값 추출 ==> 영화 자세히보기 주소값
			// https://movie.naver.com/movie/bi/mi/basic.nhn?code=181925
			String detailUrl = "https://movie.naver.com" + titleList.get(i).attr("href");
			
			// div.thumb a img의 src속성값을 추출 ==> 영화 이미지 URL
			String imgUrl = imgList.get(i).attr("src");
			
			naverList.add(NaverMovieVO.builder().m_rank(i+1).m_title(title).m_detail_url(detailUrl).m_image_url(imgUrl).build());
			
			log.debug("###영화제목 : " + title);
		}// for end
		
		return naverList;
	}
	
}

