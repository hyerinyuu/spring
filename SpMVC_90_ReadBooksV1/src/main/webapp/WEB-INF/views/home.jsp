<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
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

<style>	
	
	.table-view{
		width: 80%;
		margin: 0 auto;
	}
	.jumbotron{
		margin-bottom: 0;
	}
	
	.nav-box{
		margin-bottom: 60px;
	}
	.content-body{
		cursor: pointer;
	}
	
	#btn-insert{
		position: absolute;
		right: 200px;
		margin: 1rem;
	}
</style>	

<script>

$(function(){
	
	$("#btn-insert").click(function(){
		document.location.href = "${rootPath}/books/insert"
	})
	
	$("#btn-rbinsert").click(function(){
		document.location.href = "${rootPath}/rbooks/insert"
	})
	
	$("#btn-viewrblist").click(function(){
		document.location.href = "${rootPath}/rbooks/list"
	})
	$(".content-body").click(function(){
		let bcode = $(this).attr("data-id")
		document.location.href="${rootPath}/books/viewdetail?bcode=" + bcode
	})
	
})
</script>
					
<body>
<header>
	<div class="jumbotron text-center" id="main-header">
		<h3>도서 리스트</h3>
	</div>
</header>
<%@ include file="/WEB-INF/views/main-nav.jsp" %>
  
<%@ include file="/WEB-INF/views/books/list.jsp"%>

<button type="button" id="btn-insert" class="btn btn-primary">도서정보추가</button>

</body>
</html>