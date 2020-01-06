var file_up = function(formData) {

$.ajax({
	url : rootPath + "/rest/file_up",
	method : 'POST',
	data : formData,
	// 내가 올리는 데이터를 특별한 방법으로 가공하지 마라(processData와 contentType은 파일업로드시 필수옵션)
	processData : false,
	contentType : false,

	success : function(result) {
		if (result == 'FAIL') {
			alert("파일 업로드 오류")
		} else {
			$("#img_file").val(result)
			$("#img_view").css("display", "block")
			$("#img_view").attr("src", rootPath + "/images/" + result)
			$("#d_d_box h3").text("파일 업로드 성공!")
		}
	},

	error : function() {
		alert("서버 통신 오류")
	}
})
}