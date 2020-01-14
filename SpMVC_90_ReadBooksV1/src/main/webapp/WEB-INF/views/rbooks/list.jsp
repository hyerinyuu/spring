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
<<<<<<< HEAD
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
=======
<style>

>>>>>>> e58f25e4f22b7e4b15d9ec7f7e0cb6c89fe87e93
	.table-view{
		width: 80%;
		margin: 0 auto;
	}
	
<<<<<<< HEAD
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

=======
	a.btn {
		border-radius: 3px;
		padding: 5px 11px;
		color: #fff;
		display: inline-block;
		background-color: #A2CD0C;
		border : 1px solid #A2CD0C;
		vertical-align: middle;
		text-decoration: none;
		margin: 5px;
		
	}
	
	div.btn-box{
		width : 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	a.btn:hover {
		/* border: 2px solid black; */
		box-shadow: 5px 5px 8px rgba(80,80,80,0.8)
	}

</style>
<body>
>>>>>>> e58f25e4f22b7e4b15d9ec7f7e0cb6c89fe87e93
<section>
	<div class="table-view">
		<table class="table table-hover" id="viewtable">
			<thead class="thead-dark">
				<tr>
<<<<<<< HEAD
					<th>번호</th>
=======
>>>>>>> e58f25e4f22b7e4b15d9ec7f7e0cb6c89fe87e93
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
<<<<<<< HEAD
							<td colspan="7">독서록 불러오기 실패</td>
=======
							<td colspan="6">데이터가 없음</td>
>>>>>>> e58f25e4f22b7e4b15d9ec7f7e0cb6c89fe87e93
						</tr>
					</c:when>

					<c:otherwise>
<<<<<<< HEAD
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
=======
						<c:forEach items="${RBLIST}" var="rbdto">
							<tr class="content-body" data-id="${rbdto.rb_seq}">
								<td>${userDTO.m_id}</td> <!-- userDTO.m_id items안적어줘도됨 -->
								<td>${rbdto.rb_bcode}</td>
								<td>${rbdto.rb_bname}</td>
								<td>${rbdto.rb_date}</td>
								<td>${rbdto.rb_text}</td>
								<td>${rbdto.rb_star}</td>
							</tr>
>>>>>>> e58f25e4f22b7e4b15d9ec7f7e0cb6c89fe87e93
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>	
</section>

<<<<<<< HEAD
<button type="button" id="btn-rbinsert" class="btn btn-primary">독서록 추가</button>

=======

<div class="btn-box"> 
	<a href="javascript:void(0)" class="btn" id="btn-update">수정</a>
	<a href="javascript:void(0)" class="btn" id="btn-delete">삭제</a>
	<a href="javascript:void(0)" class="btn" id="btn-list">목록으로</a>
</div>
>>>>>>> e58f25e4f22b7e4b15d9ec7f7e0cb6c89fe87e93
</body>
</html>