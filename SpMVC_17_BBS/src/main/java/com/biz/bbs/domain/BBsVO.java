package com.biz.bbs.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("bbsVO")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BBsVO {

	private long bbs_id;		//  bigint
	private long bbs_p_id;	//	bigint
	private String bbs_writer;	//	varchar(10)
	private String bbs_date;	//	varchar(10)
	private String bbs_time;	//	varchar(10)
	private String bbs_subject;	//	varchar(10)
	private String bbs_content;	//	varchar(10)
	private int bbs_count;		//	int

	List<BBsVO> bbs_reply;
}
