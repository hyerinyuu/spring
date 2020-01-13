<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 상세정보</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>

	.table-view{
		width: 80%;
		margin: 0 auto;
	}
	
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

<script>
	$(function(){
		
		// 도서 리스트로 다시 돌아가는 '목록으로' 버튼 클릭시 script
		$("#btn-list").click(function(){
			document.location.href = "${rootPath}/books/list"
		})
		
		$("#btn-update").click(function(){
			
			if(confirm("도서정보를 수정하시겠습니까?"))
			document.location.href = "${rootPath}/books/update?id=${bDTO.b_code}"
		})
		
		$("#btn-delete").click(function(){
			if(confirm("도서정보를 삭제하시겠습니까?"))
			document.location.replace("${rootPath}/books/delete?id=${bDTO.b_code}")	
			
		})
	})

</script>
</head>
<body>

<header>
	<div class="jumbotron text-center">
		<h3>도서 상세정보</h3>
	</div>
</header>	

<section>
	<div class="table-view">
		<table class="table table-striped">
		 	
		 	<thead class="thead-dark">
				<tr>
					<th>ISBN</th><td colspan="4">${bDTO.b_code}</td>
				</tr>
				
				<tr>
					<th>도서명</th><td>${bDTO.b_name}</td>
					<th>저자</th><td>${bDTO.b_auther}</td>
				</tr>
				
				
				<tr>
					<th>출판사</th><td>${bDTO.b_comp}</td>
					<th>구입일자</th><td>${bDTO.b_year}</td>
				</tr>

				<tr>
					<th>구입가격</th><td colspan="4">${bDTO.b_iprice}</td>
				</tr>
				
			</thead>	
		</table>
	</div>		
	
<section>
	<div class="table-view">
		<table class="table table-striped">
		 	
		 	<thead class="thead-dark">
				<tr>
					<th>ISBN</th><td colspan="4">${rbDTO.rb_bcode}</td>
				</tr>

				<tr>
					<th>도서제목</th><td>${rbDTO.rb_bname}</td>
					<th>독서일자</th><td>${rbDTO.rb_date}</td>
				</tr>
				
				
				<tr>
					<th>한줄소감</th><td>${rbDTO.rb_subject}</td>
				</tr>

				<tr>
					<th>별점</th><td colspan="4">${rbDTO.rb_star}</td>
				</tr>
				
			</thead>	
		</table>
	</div>			
	<!--  
		<table>
			<caption>독서기록 상세정보</caption>
	
				<tr>
					<th>No</th><td>${rbDTO.b_code}</td>
					<th>도서코드</th><td>${rbDTO.b_name}</td>
					<th>독서일자</th><td>${rbDTO.b_auther}</td>
				</tr>
				
				<tr>
					<th>독서시작시각</th><td>${rbDTO.b_comp}</td>
					<th>독서시간</th><td>${rbDTO.h_year}</td>
				</tr>
				
				<tr>
					<th>한줄소감</th><td>${rbDTO.b_comp}</td>
					<th>긴줄소감</th><td>${rbDTO.h_year}</td>
					<th>별점</th><td>${rbDTO.h_year}</td>
				</tr>
		</table>
	 -->

</section>

<div class="btn-box"> 
	<a href="javascript:void(0)" class="btn" id="btn-update">수정</a>
	<a href="javascript:void(0)" class="btn" id="btn-delete">삭제</a>
	<a href="javascript:void(0)" class="btn" id="btn-list">목록으로</a>
</div>


</body>
</html>