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
</style>	

<script>

$(function(){
	
	$("#btn-insert").click(function(){
		document.location.href = "${rootPath}/books/insert"
	})
	
	$(".content-body").click(function(){
		let bcode = $(this).attr("data-id")
		document.location.href="${rootPath}/books/viewdetail?bcode=" + bcode
	})
	
})
</script>
					
<body>
<header>
	<div class="jumbotron text-center">
		<h3>독서록 리스트</h3>
	</div>
</header>	

<%@ include file="/WEB-INF/views/books/list.jsp"%>


<button type="button" id="btn-insert">도서정보 추가</button>


</body>
</html>