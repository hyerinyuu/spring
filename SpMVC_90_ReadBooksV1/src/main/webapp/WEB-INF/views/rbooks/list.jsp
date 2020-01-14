<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>독서록 리스트</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<script>

$(function(){
	
	$(".content-body").click(function(){
		let rb_seq = $(this).attr("data-id")
		document.location.href="${rootPath}/rbooks/rbooks/viewdetail?id=" + rb_seq
	})
	
		
	$("#btn-rbinsert").click(function(){
		document.location.href = "${rootPath}/rbooks/rbooks/insert"
	})
	
})

</script>
<style>
	.table-view{
		width: 80%;
		margin: 0 auto;
	}
	
	.content-body{
		cursor: pointer;
	}
	
	button{
		position: absolute;
		right: 200px;
		margin: 1rem;
	}
</style>
<body>

<header>
	<div class="jumbotron text-center" id="main-header">
		<h3>독서록 리스트</h3>
	</div>
</header>
<%@ include file="/WEB-INF/views/main-nav.jsp" %>

<section>
	<div class="table-view">
		<table class="table table-hover" id="viewtable">
			<thead class="thead-dark">
				<tr>
					<th>번호</th>
					<th>사용자ID</th>
					<th>ISBN</th>
					<th>도서명</th>
					<th>독서일자</th>
					<th>한줄소감</th>
					<th>별점</th>
				</tr>
			</thead>	
			
			<c:choose>
					<c:when test="${empty RBLIST}">
						<tr>
							<td colspan="7">독서록 불러오기 실패</td>
						</tr>
					</c:when>

					<c:otherwise>
						<c:forEach items="${RBLIST}" var="rbdto" varStatus="status">
								<tr class="content-body" data-id="${rbdto.rb_seq}">
									<td>${status.count}</td>
									<td>${memberDTO.m_id}</td> 
									<td>${rbdto.rb_bcode}</td>
									<td>${rbdto.rb_bname}</td>
									<td>${rbdto.rb_date}</td>
									<td>${rbdto.rb_subject}</td>
									<td>${rbdto.rb_star}</td>
								</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>	
</section>

<button type="button" id="btn-rbinsert" class="btn btn-primary">독서록 추가</button>

</body>
</html>