<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style>

	.jumbotron{
		margin-bottom: 0;
	}
	
	.nav-box{
		margin-bottom: 50px;
	}

</style>

<div class="nav-box">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	
	  <ul class="navbar-nav">
	  	<li class="nav-item">
	      <a class="nav-link" href="${rootPath}/">홈</a>
	    </li>
	  
	    <li class="nav-item">
	      <a class="nav-link" href="${rootPath}/rbooks/list">독서록리스트</a>
	    </li>
	    
	    <li class="nav-item">
	      <a class="nav-link" href="${rootPath}/member/login">로그인</a>
	    </li>
	    
	    <li class="nav-item">
	      <a class="nav-link" href="${rootPath}/member/join">회원가입</a>
	    </li>
	    
	  </ul>
	</nav>
</div>
