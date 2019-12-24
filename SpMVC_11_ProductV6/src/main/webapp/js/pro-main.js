$(function() {
	
	$("#search-input").on("search", function(){
		$("#search-form").submit()
	})
	
	$("#pro-img-delete").click(
			function() {
				if (confirm("대표 이미지를 삭제할까요?")) {
					let p_code = $("#p_code").val()
					document.location.href = rootPath + "/proImgDelete?p_code="
							+ p_code
				}
			})
	
	// jquery 2.x 미만에서 사용한 코드(현재 사용 불가, 없어짐)
	// $("img.sub-img").live(function())
	
	// jquery 1.7 이상에서 사용하던 코드 
	// $("img.sub-img").on("click", function())
	
	// 현재 버전에서 사용하는 코드	
	// jquery에서 동적으로 생성한 tag에 이벤트를 주는 방법(좀 더 자세하게 위치를 명시해줘야함)
	// 동적 tag란 : 스크립트로 임의로 추가(append)한 tag
	// document : 이 html문서 자체를 의미함
	// => html문서에 click이벤트를 설정하고 혹시 클릭된 요소가 img.sub-img인지 확인하고 맞으면 alert를 주는 코드임
	$(document).on("click", "span.img-delete", function(){
		
		let file_name = $(this).attr("data-name")
		if(confirm(file_name + "이미지를 삭제합니다")){
			let file_seq = $(this).attr("data-id")
			
			$.ajax({
				
				url: rootPath + "/rest/subImgDelete",
				data : {file_seq : file_seq},
				success : function(p_code){
					getProInfo(p_code)
				}
			})
		}
	})
	
	$(document).on("click", ".img-view", function(){
		
		let file_name = $(this).attr("data-upload")
		window.open(rootPath + "/files/" + file_name, "_new", "toolbar=no,scrollbars=no")
		
	})
	
	// 코딩으로 만든 tag에 이벤트를 주는 방법
	$("tr.p-row").click(function(){
		let p_code = $(this).attr("data-id")
		// 현재 클릭된 tr 전체 DOM을 tr변수에 담아라
		let tr = $(this)
		
		// tr이 담고있는 td를 배열로 생성하라 (이코드는 테이블에 내가 보고자하는 칼럼이 모두 있을때만 가능)
		let tds = tr.children()
		let tp_code = tds.eq(0).text()
		let tp_name = tds.eq(1).text()
		let tp_iprice = tds.eq(2).text()
		let tp_oprice = tds.eq(3).text()
		
		$("#p_code").val(tp_code)
		$("#p_name").val(tp_name)
		$("#p_iprice").val(tp_iprice)
		$("#p_oprice").val(tp_oprice)
		
		// http://localhost:8080/product/getFiles?p_code=P0402
		$.ajax({
			url : rootPath + "/getFiles",
			data : {p_code : p_code},
			success : function(result){
				$("p_sub_list").html("")
				$("#p_sub_list").html(result)
			}
			
		})
		
		// getProInfo(p_code)
	})	
	
	var getProInfo = function(p_code){
		$.ajax({
			// "getProduct?p_code=" + p_code
			url : rootPath + "/rest/getProduct",
			data : {p_code : p_code}, 		 // 보내는 이름과 선언한 변수이름이 같으면 그냥 p_code만 써도 되는데 크롬은 잘 안먹음
			dataType : 'json',
			success : function(result){
				$("#p_code").val(result.p_code)
				$("#p_name").val(result.p_name)
				$("#p_iprice").val(result.p_iprice)
				$("#p_oprice").val(result.p_oprice)
				$("#p_file").val(result.p_file)
				
				let profileImage = rootPath + "/files/noimage.png"
				if(result.p_file != null){
					profileImage = rootPath + "/files/" + result.p_file
				}
				$("#p_image").attr("src", profileImage)
				$("#pro-img-search").attr("data-upload", result.p_file)
				
				if(result.p_vat == "1"){
					$("#p_vat").attr("checked", "checked")
				}else{
					$("#p_vat").attr("checked", "")
				}
				$("#p_sub_list").html("")
				if(result.p_files.length > 0){

					result.p_files.forEach(function(file){
					
						var imgTag =
							$("<img/>",{
								width:"100px",
								height:"100px",
								margin: "10px",
								"class" : "sub-img",
								"data-id" : file.file_seq,
								"data-name" : file.file_origin_name,
								"alt" : file.file_origin_name,
								src : rootPath + "/files/" + file.file_upload_name
							})
							
						var delTag =
							$("<span/>",
									{
									"class": "img-delete",
									"data-id" : file.file_seq,
									"data-name" : file.file_origin_name,
							}).append($("<i/>",{"class":"fa fa-trash"} ))
						var viewTag =
							$("<span/>",
									{
									"class": "img-view",
									"data-id" : file.file_seq,
									"data-name" : file.file_origin_name,
									"data-upload" : file.file_upload_name,
							}).append($("<i/>",{"class":"fa fa-search"} ))
						
						$("#p_sub_list").append(
							$("<div/>",{"class" : "sub-img-box"}).append(
							
								imgTag,
								$("<div/>",{"class":"img-menu"}).append(
									delTag,viewTag
								)
							)
						)
					})
				}
			},
			error : function(){
				alert("서버 통신 오류")
			}
		})
	}
	
	if(bStart == 0){

		// inputBox에 있는 상품코드
		var p_code = $("#p_code").val()
		// 없으면 리스트 첫번째 항목에서 추출한 상품코드
		if(p_code == ""){
			// list table의 첫번째 tr을 가져와라
			let tr = $("tr.p-row:eq(0)")
			p_code = tr.attr("data-id")
		}
		// 상품코드를 getProInfo() 함수에 전달하여
		// input box에 값을 초기화 시켜라
		getProInfo(p_code)
		bStart ++
	}
	
})
