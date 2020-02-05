package com.biz.bbs.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("cmmVO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentVO {

	private long cmt_id;
	private long cmt_p_id;
	private String cmt_writer;
	private String cmt_date;
	private String cmt_time;
	private String cmt_text;

}
