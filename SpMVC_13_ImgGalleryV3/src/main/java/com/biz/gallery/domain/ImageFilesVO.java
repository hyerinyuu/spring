package com.biz.gallery.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// vo 패키지를 scan했을때 이름을 간단하게 줄이기 위해
@Alias("fileVO")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageFilesVO {

	private long img_file_seq;				// number
	private long img_file_p_code;			// number
	private String img_file_origin_name;	// nvarchar2(255 char)
	private String img_file_upload_name;	// nvarchar2(255 char)

}
