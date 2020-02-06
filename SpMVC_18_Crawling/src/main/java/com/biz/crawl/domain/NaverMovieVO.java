package com.biz.crawl.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("nVO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverMovieVO {

	private long id;
	private int m_rank;
	private String m_detail_url;
	private String m_image_url;
	private String m_title;
}
