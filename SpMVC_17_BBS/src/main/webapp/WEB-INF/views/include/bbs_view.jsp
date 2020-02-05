<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>

<script>
$(function(){
	
	// post를 가장 먼저 넣은 이유 : 현재 페이지가 모두 그려진 후 바로 실행하라(ajax여서 post방식으로 호출이 가능한 것)
	// html방식으로 값을 채워 넣어라
	$.post("${rootPath}/bbs/cmt_list", {cmt_p_id:"${bbsVO.bbs_id}"}, function(result){
		$("#cmt_list").html(result)
		
	})
	
	$("button.btn-c-save").click(function(){
		let cmt_writer = $("#cmt_writer").val()
		let cmt_text = $("#cmt_text").val()
		
		if(cmt_writer == ""){
			alert("댓글 작성자를 입력하세요")
			cmt_writer.focus()
			return false
		}
		
		if(cmt_text == ""){
			alert("댓글내용을 입력하세요")
			cmt_text.focus()
			return false
		}
		
		// json형태로 데이터 생성
		let cmt_data = {
				cmt_p_id : '${bbsVO.bbs_id}',
				cmt_writer:cmt_writer,
				cmt_text:cmt_text
				}
		
		$.ajax({
			url: '${rootPath}/bbs/cmt_write',
			method: 'POST',
			data: cmt_data,
			success: function(result){
				/* alert(result) */
				$("#cmt_list").html(result)
			},
			error:function(){
				alert("서버 통신오류")
			}
			
		})
		
			
	})
	
})
</script>
<style>
	.table-view{
		width: 80%;
		height: 300px;
		margin: 0 auto;
		margin-top: 4rem;
	}
	
	#btn-delete, #btn-update, #btn-comment{
		margin-left: 8px;
	}
	
	#btn-list, #btn-delete, #btn-update, #btn-comment{
		margin-top: 2rem;
	}
	
	#bbs_reply{
		margin: 0 auto;
		margin-top: 3rem;
	}
	
	.comment-input{
		width: 100%;
		border-radius: 5px;
	}
	
	.btn-c-save {
		margin-top: 1rem;
	}
	
	
</style>

</head>
<body>

<section>
	<div class="table-view">
		<table class="table table-striped">
		 	<thead class="thead-dark" >
				<tr data-seq="${bbsVO.bbs_id}">
					<th width="20%">작성자</th><td colspan="4">${bbsVO.bbs_writer}</td>
				</tr>
				
				<tr>
					<th width="20%">작성시각</th><td colspan="2">${bbsVO.bbs_time}</td>
					<th>작성일</th><td colspan="2">${bbsVO.bbs_date}</td>
				</tr>
				
				<tr>
					<th width="20%">제목</th><td colspan="2">${bbsVO.bbs_subject}</td>
					<th>조회수</th><td colspan="2">${bbsVO.bbs_count}</td>
				</tr>
				
				<tr>
					<th width="20%">내용</th><td colspan="2">${bbsVO.bbs_content}</td>
				</tr>
			</thead>
		</table>
	
		
		<div class="card-body">
		<h3>Comment</h3>
		<hr>
			<div id="cmt_list" class="form-group bg_white">
				댓글 리스트 보기
			</div>
			
			<div class="form-group">
				<input id="cmt_writer" name="cmt_writer" class="form-control" placeholder="작성자" >
			</div>	
			<div class="form-group">
				<input id="cmt_text" name="cmt_text" class="form-control" >
				<button class="btn btn-primary btn-c-save">저장</button>							
			</div>		
		</div>
		
		
		<div class="d-flex">
			<button type="button" class="btn btn-dark" data-seq="${bbsVO.bbs_id}" id="btn-list" onclick="location.href='${rootPath}/bbs/list'">리스트보기</button>		
			<button type="button" class="btn btn-dark ml-auto" data-seq="${bbsVO.bbs_id}" id="btn-update" onclick="location.href='${rootPath}/bbs/save'">수정</button>
			<button type="button" class="btn btn-warning" data-seq="${bbsVO.bbs_id}" id="btn-delete" onclick="location.href='${rootPath}/bbs/delete'">삭제</button>
		</div>	
		
		<!--  bbs_p_id == 0일일때만 조건을 추가하면 원글에만 답글 기능이 보이게 됨(답글에는 reply inputbox가 보이지 않게 됨 -->
		<c:if test="${bbsVO.bbs_p_id == 0}">		
			<script>
				$(function(){
					
					$("button.btn-r-save").click(function(){
						let bbs_writer = $("#bbs_writer").val()
						let bbs_content = $("#bbs_content").val()
						
						if(bbs_writer == ""){
							alert("작성자를 입력하세요")
							$("#bbs_writer").focus()
							return false
						}
						
						if(bbs_content == ""){
							alert("본문을 입력하세요")
							$("#bbs_content").focus()
							return false
						}
						
						$("form").submit()	
					})
					
				})
			
			</script>
			<form:form action="${rootPath}/bbs/reply" modelAttribute="bbsVO" id="bbs_reply">
				<h3>Reply</h3>
				<hr>
				<div class="form-group">
				<!--  spring form tag가 아닌 html input은 id를 가져오지 못하기 때문에 script의 .val() method를 사용하기 위해서는
				input tag에 id값을 추가해줘야한다. -->
					<input class="form-control" id="bbs_writer" name="bbs_writer" value="" placeholder="작성자 닉네임" />
				</div>
				<textarea name="bbs_content" id="bbs_content" class="comment-input"></textarea>
				<div class="d-flex">
					<button type="button" class="btn btn-primary btn-r-save ml-auto">저장</button>
				</div>
			</form:form>
		</c:if>	
	</div>	
	
</section>	


</body>
</html>