var files_up = function(formData) {

$.ajax({
	url : rootPath + "/image/files_up",
	method : 'POST',
	data : formData,
	// 내가 올리는 데이터를 특별한 방법으로 가공하지 마라(processData와 contentType은 파일업로드시 필수옵션)
	processData : false,
	contentType : false,

	success : function(result) {
		if (result == 'FAIL') {
			alert("파일 업로드 오류")
		} else {
			$("#d_d_box").html(result)	
		}
	},
	error : function() {
		alert("서버 통신 오류")
	}
})
}