<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style>
	body{
		border: 1px solid #aaa;
	}
	
	header {
		width: 95%;
		border-radius: 8px;
		margin: 0 auto;
		margin-top: 2rem;
	}
</style>

<script>
$(function(){
	
	$("#btn-write").click(function(){
		document.location.href="${rootPath}/bbs/write"
	})
	
})


</script>

</head>

<header class="jumbotron text-center" >
	<h3>Build Board System</h3>
</header>

<ul class="nav">
	<li class="nav-item active"><a class="nav-link" href="${rootPath}/">Home</a></li>
	<li class="nav-item active justify-content-end">
    	<a class="nav-link" href="/member/login">Login</a>
    </li>
    <li class="nav-item active">
    	<a class="nav-link" href="/member/join">Join</a>
  	</li>
</ul>

<body class="container-fluid">
	<div class="input-group">
		<div class="input-group-btn">
			<button class="btn btn-default" id="btn-write" type="button">게시판 작성</button>
		</div>	
	</div>
</body>
</html>