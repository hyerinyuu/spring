<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<style>
	header {
		background-color: rgba(0,255,0,0.2);
		padding: 1rem;
		color: black;
	}
	
	nav{
		display: flex;
		background-color: green;
		padding: 14px 16px;
	}
	
	nav button {
		background-color: rgba(0,0,255,0.2);
		padding: 14px 16px;
		color: black;
		font-size: 15px;
		font-weight: bold;
		outline: 0;
	}
	
	section.main-container{
	
		display: flex;
		flex-wrap: wrap;
	}
	
	div.d-box {
		width: 300px;
		margin: 20px;
		border: 1px solid red;
		border-radius: 10px;
		paddig: 10px 16px;
	
	}	
	
	a.title {
	
		color: inherit;
		text-decoration: none;
	}
	
	div.d-box:hover {
	
		background-color: #ddd;	
	}
	
	b {
		color: red;
		
	}
	
	input, select {
		
		padding: 8px;
		margin: 5px;
	}
	
	p.title {
		font-size: 20px;	
		font-weight: bold;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}

</style>
</head>
<body>
<header><h3>네이버검색</h3></header>

<nav>
	<form action="${rootPath}/search">
		<select name="cat">
			<option value="news">뉴스</option>
			<option value="books">도서</option>
			<option value="movies">영화</option>
		</select>
		<input name="search" placeholder="검색어를 입력하시고 Enter를 누르세요!">
		<button>검색</button>
	</form>
</nav>

<section class="main-container">
	<c:forEach items="${NAVER_ITEMS}" var="item">
	
		<a href="${item.link}" target="_new" class="title">
			<div class="d-box">
				<c:if test="${item.image !=  null}">
					<img src = "${item.image}">
				</c:if>	
				<p class="title">${item.title}</p>
				<p>${item.description}</p>
			</div>
		</a>
		
	</c:forEach>
</section>
<section>
	<%@ include file="/WEB-INF/views/pagination.jsp" %>


</section>

</body>
</html>