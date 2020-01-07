<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 갤러리</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<!--  메시지를 한글로 바꿔주는 script(font는 맑은 고딕만 지원) -->
<script src="${rootPath}/javascript/summernote-ko-KR.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.contextMenu.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.contextMenu.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.ui.position.min.js"></script>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<script>
	var rootPath = "${rootPath}"
</script>

<script src="${rootPath}/javascript/img_upload.js"></script>
<script src="${rootPath}/javascript/imgs_upload.js"></script>

<script>
$(function(){
	
	var toolbar = [
		['style',['bold', 'italic', 'underline'] ],
		['fontsize',['fontsize']],
		['font Style',['fontname']],
		['color', ['color']],
		['para',['ul','ol','paragragh']],
		['height',['height']],
		['table', ['table']],
		['insert', ['link','hr', 'picture']],
		['view', ['fullscreen', 'codeview']]
		
		
	]
	
	$("#img_text").summernote({
		lang: 'ko-KR',
		placeholder: '본문을 입력하세요',
		width: '100%',
		toolbar: toolbar,
		height: '200px',
		disableDragAndDrop: true,
	})
	
	
	$("#btn-img-up").click(function(){
		document.location.href = "${rootPath}/image/upload"
			
	})	
	
	$("#d_d_box").on('dragover', function(e){
		$("#d_d_box h3").text("파일을 내려 놓으세요!")
		return false
	})
	
	/*
	file 1개를 Drag and Drop으로 업로드 수행하기
	*/
	$("#d_d_box").on('drop', function(e){
		$("#d_d_box h3").text("파일 업로드중...")
		
		// drop한 파일 리스트 추출(여러개)
		let files = e.originalEvent.dataTransfer.files

		// 멀티파일업로드
		let fileLen = files.length
		// if(fileLen > 1){
			
			// 멀티파일업로드를 위한 객체 생성
			let formData = new FormData()
			
			// drop한 파일들을 모두 추가
			for(let i = 0; i < files.length ; i++){

				// file을 하나씩 append(for문으로 파일 개수만큼 반복)
				formData.append('files', files[i])
			}
			files_up(formData)
			return false;
		
		/*	
		// 싱글파일업로드	
		}else{
			// 리스트의 첫번째 파일만 추출(한개)
			let file = files[0]
	
			// 추출된 파일 정보를 서버에 먼저 upload
			// js FormData 클래스를 사용해서 서버에 파일 업로드 준비
			// (파일은 용량이 커서 js만으로 다루기 힘들어서_)
			let formData = new FormData()
			formData.append('file', file)
			
			file_up(formData)
		}
		*/
		return false;
	})
	
	var contextCallBack = function(key, options){
		if(key == 'edit'){
			
			let img_seq = $(this).attr("data-seq");
			document.location.href = "${rootPath}/image/update/" + img_seq
			
		}
		
		if(key == 'delete'){
			if(confirm("이미지를 삭제할까요?")) {
				let img_seq = $(this).attr("data-seq");
				
				/* delete가 현재 get방식이라서 주소창에 delete + seq를 임의로 적어도 삭제가 되는 상태이므로
				type을 post로 변경해서 update */
				$.ajax({
					url : "${rootPath}/image/delete",
					// data : img_seq
					// data : img_seq = 3
					data : {img_seq : img_seq},
					type: 'POST',
					success : function(result){
						if(result < 1)
							alert("삭제도중 문제 발생")
					},
					error : function(){
						alert("서버 통신 오류")
					}
				})
				// 새로고침해서 home으로 가라
				document.location.replace("${rootPath}/")
				
				// 이벤트 버블링 금지
				return false;
				
				
				// GET 방식
				//  : document.location.href = "${rootPath}/image/delete/" + img_seq
			}
		}
	}
	
	/*
	jquery응용
	마우스를 제어해서 contextMenu(오른쪽 마우스 클릭시 표시할 메뉴)를
	만들어 주는 tool
	*/
	$.contextMenu({
		/*
		.img_card를 클릭(select)하면 edit와 delete 메뉴를 표시해라
		*/
		selector:'.img_card',
		/*
		마우스로 클릭하면 CallBack이라는 함수를 실행하라
		*/
		callback : contextCallBack,
		items : {
			'edit' : {name:'수정', icon: 'edit'},
			'delete' : {name:'삭제', icon: 'delete'},
		}
		
	})

	var img_context = function(key, option){
		let img_file_seq = this.attr("data-id")
		if(key == 'delete'){
			if(confirm(img_file_seq + "이미지를 삭제할까요?")) {
				
				$.ajax({
					url : "${rootPath}/image/deletefileseq",
					data : {img_file_seq : img_file_seq},
					type: 'POST',
					success : function(result){
						if(result < 1)
							alert("삭제도중 문제 발생")
					},
					error : function(){
						alert("서버 통신 오류")
					},
				})
				document.location.replace(document.location.href)
				
				return false;
			}
		} else if(key == "copy"){
			
		} else if(key == "loading"){
			let file = $(this).find("img").attr("src")
			if(confirm("확대하여 보시겠습니까?")){
				window.open(file, "target=_new")	
			}
		}
		
	}
	$.contextMenu({
		/*
		.img_card를 클릭(select)하면 edit와 delete 메뉴를 표시해라
		*/
		selector:'.img_view',
		/*
		마우스로 클릭하면 CallBack이라는 함수를 실행하라
		*/
		callback : img_context,
		items : {
			'loading' : {name:'확대보기', icon: 'fas fa-eye'},
			'copy' : {name:'다운로드', icon: 'copy'},
			'delete' : {name:'삭제', icon: 'delete'},
			'search' : {name:'검색', icon: 'fas fa-search'}
		}
		
	})
	
})	
</script>

<style>
	*{
		box-sizing: border-box;
		margin: 0px;
		padding: 0px;
	}
	
	body{
		width: 100%;
	}
	
	header{
		background-color: #41D3BD;
		margin: 0;
		padding: 2rem;
		color: white;
	}
	
	section{
		width: 90%;
		margin: 10px auto;
	}
	
	#img_box{
		margin: 10px auto;
		border: 1px solid green;
		display: flex;
		flex-wrap: wrap;
	}
	
	.img_panel{
		padding: 0.01rem 16px;
		margin-top: 16px;
		margin-bottom: 16px;
	}

	.img_card{
		width: 200px;
		height: 200px;
		margin: 10px;
		
		/* 이미지가 cardBox보다 클 때 이미지 자르기 */
		overflow: hidden;
		
		display: flex;
		justify-content: center;
		flex-flow: column;
		box-shadow: 0 4px 10px 0 rgba(0,0,0,0.16), 0 4px 20px 0 rgba(0,0,0,0.19);
	}
	
	.img_card .img_title {
		padding: 0.5rem;
		text-align: center;
	}
	
	.bz-button{
		padding: 8px 16px;
		border: none;
		display: inline-block;
		vertical-align: middle;
		text-decoration: none;
		color: gray;
		background-color: lightblue;
		text-align: center;
		cursor: pointer;
		white-space: nowrap;
		margin: 5px;
	}
	
	.bz-button:hover{
		box-shadow: 0 8px 16px rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.2);
	}
	
	a{
		text-decoration: none;
		color: inherit;
	}
	
	div.input_box {
		margin: 5px auto;
		width: 90%;
	}
	
	input[type="text"]{
		padding: 8px;
		display: block;
		border: none;
		border: 1px solid #ccc;
		border-radius: 5px;
		width: 100%;
		margin: 8px auto;
	}
	
	#img_title {
		margin: 8px auto;
	}
	
	#d_d_box {
		width: 100%;
		height: 300px;
		color: #aaa;
		border: 1px solid red;
		display: flex;
		justify-content: center; 
		align-items: center;
	}
	
	#img_view {
		display: none;
	}

	.img_list{
		display: flex;
		flex-wrap: wrap;
	}
	
	.img_view img {
		width: 100px;
		height: 100px;
		margin: 5px;
	}
	
</style>

</head>
<body>

<header>
	<h3>이미지 갤러리</h3>
</header>

<c:choose>
	
	<c:when test="${BODY == 'UPLOAD'}">
		<section>
			<%@ include file="/WEB-INF/views/body/image_upload.jsp" %>
		</section>
	</c:when>
		
	<c:otherwise>
		<section id="img_box">
			<c:forEach items="${imgList}" var="img">
				<%@ include file="/WEB-INF/views/include/img_card.jsp" %>
			</c:forEach>
		</section>>		
	</c:otherwise>
		
</c:choose>


<section>
	<button id="btn-img-up" class="bz-button">이미지 올리기</button>
</section>

</body>
</html>