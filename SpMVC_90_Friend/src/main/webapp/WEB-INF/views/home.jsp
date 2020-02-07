<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyAddressBook</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<style>
	#h-item {
		cursor: pointer;
		padding: 10rem;
	}
	#list-table{
		top: 3rem; 
		width: 95%;
		margin: 0 auto;
	
	}
	.dummy-blank{
		padding: 2rem;
	}
	
	button {
		margin-left: 10px;
	}
	
	#btn-search{
		margin: 0 auto;
	}
	#searchtype{
    	width: 120px;
    	margin : 0;
    }
    
    .search-box{
    	width: 600px;
    }
    
    
</style>
<script>
	$(function(){
		$("#h-item").click(function(){
			document.location.href = "${rootPath}/"
		})
	})

</script>

<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
  <a class="navbar-brand" href="${rootPath}/">MyAddressBook</a>
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Addresses</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Login</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Join</a>
    </li>
  </ul>
</nav>
<header>
	<div class="jumbotron text-center" id="h-item">
	  	<h1>My Address Book</h1>
	</div>
</header>
<c:choose>
	<c:when test="${BODY == 'INSERT'}">
		<%@ include file="/WEB-INF/views/friend/insert.jsp" %>
	</c:when>
	<c:when test="${BODY == 'VIEW'}">
		<%@ include file="/WEB-INF/views/friend/view.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/WEB-INF/views/friend/list.jsp" %>
		<!--  검색창 -->
		<div class="container">
		  <form>
		    <div class="form-group search-box ml-auto mr-auto row">
			    <select class="form-control" col-4 id="searchtype" name="searchtype">
			     	<option>이름</option>
			   		<option>전화번호</option>
			    </select>
		 	  	<input class="form-control col-8 ml-auto mr-auto" id="keyword" name="keyword" placeholder="검색어를 입력하세요">
		    	<button class="btn btn-primary" id="btn-search">검색</button>
		    </div>
		  </form>
		</div>
	</c:otherwise>
</c:choose>
</body>
</html>