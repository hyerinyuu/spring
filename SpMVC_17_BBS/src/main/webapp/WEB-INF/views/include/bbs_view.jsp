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
	
	#comment-input{
		width: 90%;
		border-radius: 5px;
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
					<th>조회수</th><td colspan="2">${bbsVO.bbs_content}</td>
				</tr>
				
				<tr>
					<th width="20%">내용</th><td colspan="2">${bbsVO.bbs_content}</td>
				</tr>
			</thead>
		</table>
	
		
		<div class="d-flex">
			<button type="button" class="btn btn-dark" data-seq="${bbsVO.bbs_id}" id="btn-list" onclick="location.href='${rootPath}/bbs/list'">리스트보기</button>		
			<button type="button" class="btn btn-dark ml-auto" data-seq="${bbsVO.bbs_id}" id="btn-update" onclick="location.href='${rootPath}/bbs/save'">수정</button>
			<button type="button" class="btn btn-warning" data-seq="${bbsVO.bbs_id}" id="btn-delete" onclick="location.href='${rootPath}/bbs/delete'">삭제</button>
			<button type="button" class="btn btn-dark" data-seq="${bbsVO.bbs_id}" id="btn-comment" onclick="location.href='${rootPath}/bbs/delete'">댓글</button>
		</div>	
		
		
		<form:form action="${rootPath}/bbs/reply" modelAttribute="bbsVO" id="bbs_reply">
			<h3>Reply</h3>
			<hr>
			<form:textarea path="bbs_content" id="comment-input" placeholder="답글을 입력하세요" cols="" rows="10"/>
			<button class="btn btn-primary">저장</button>
		</form:form>
	</div>	
	
</section>	


</body>
</html>