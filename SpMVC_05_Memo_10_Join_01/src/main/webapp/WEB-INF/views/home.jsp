<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<script src="${rootPath}/js/jquery-3.4.1.js"></script>


<%
	/*
		query event
		btn-insert가 클릭이 되면 insert로 보내라
	*/
	

%>
<script>
$(function(){
	
	$("#btn-insert").click(function(){
		document.location.href = "${rootPath}/memo/insert"
	})
})



</script>


<style type="text/css">
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

html, body {
	height: 100%;
}

body {
	width: 978px;
	display: flex;
	flex-flow: column wrap;
	margin: 0 auto;
}

header {
	background-color: #ccc;
	text-align: center;
	padding : 0.8rem;
}

footer {
	flex: 0 0 auto;
	background-color: green;
	color: white;
	text-align: center;
	padding: 1rem;
}

section#main-body {
	/* 
		flex : 1 0 auto 
		화면 가득히 box를 채우기 위한 설정
	*/
	flex-grow: 1; 		/* 최대화 크기로 */
	flex-shrink: 0; 	/* 최소화 했을때 */
	flex-basis: auto;
	background-color: #ddd;
	display: flex;
}

section#main-body article{
	flex: 5;
}

section#main-body aside{
	flex: 1;
	border: 1px solid green;
	background-color: white;
	padding : 18px;
	border-radius: 10px;
}

section#main-body ul {
	list-style: none;
	margin-left: 16px;
}

section#main-body li a {

	/* 
	a tag에 width나 height를 설정하기 위해서는 display를 block, inline-block으로 설정해야 한다.
	*/
	display: inline-block;
	width: 120px;
	
	border-bottom: 1px solid black;
	padding: 10px 16px;
	text-decoration: none;
}

section#main-body li a:hover{
	background-color: black;
	color: white;

}

article.list {
	border: 1px solid blue;
	height: 80%;
	overflow: auto;
}

div.b-box {
	
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 0.8rem;
}

div.b-box button {
	background-color: orange;
	color: black;
	font-weight: bold;
	padding: 8px 16px;
	border: 0px;
	border-radius: 5px;
}

div.b-box button:hover {
	background-color: #ddd;
	color: black;
}

div.s-box {
	width: 100%;
	border: 1px solid black;
	margin-bottom: 5px;	 

}

div.s-box input {

	display: block;
	width: 90;
	margin: 10px auto;
	
}

nav ul {
	list-style: none;
	background-color: green;
	color: black;
	display: flex;
	
}

nav ul a {
	
	text-decoration: none;
	display: inline-block;
	padding: 8px;
	margin: 5px;
	color: inherit;
}

nav li:nth-child(2) {
	margin-left: auto;
}

nav li:nth-child(4) {
	margin-left: auto;
}


</style>
</head>
<body>
	<header>
		<h3>심플메모장</h3>
	</header>
	
	<nav>
		<ul>
			<li><a href="${rootPath}/">홈</a></li>
			<li><a href="${rootPath}/">메뉴1</a></li>
			<li><a href="${rootPath}/">메뉴2</a></li>
			
			<c:if test="${userDTO eq null}">
				<li><a href="${rootPath}/member/login">로그인</a></li>
				<li><a href="${rootPath}/member/join">회원가입</a></li>
			</c:if>
			
			<%
				/*
				<c:if test="${userDTO != null && userDTO.u_id != null}">
				*/
			%>
			<c:if test="${userDTO != null}">
				<li><a href="${rootPath}/member/logout">로그아웃</a></li>
				<li><a href="${rootPath}/member/mypage">${userDTO.u_name}</a></li>
			</c:if>
					
		</ul>
	
	</nav>

	<section id="main-body">
		<article>
			<div class="s-box">
				<form>
					<input type="text" name="search">
				</form>
			</div>
			
			<article class="list">
				<%@ include file="/WEB-INF/views/list.jsp" %>		
			</article>
			
			<div class="b-box">
				<button id="btn-insert">메모작성</button>
			</div>
				
		</article>
		<aside>
			<ul>
				<li><a href="#">오늘 할일</a></li>
				<li><a href="#">약속</a></li>
				<li><a href="#">중요메모</a></li>
				<li><a href="${rootPath}/html/hello.html">hello</a></li>
				<li><a href="${rootPath}/images/readme.txt">readme</a></li>
			</ul>
		</aside>
	</section>
	
	<footer>
		<address>CopyRight &copy; hyerin.you@gmail.com</address>
	</footer>

</body>
</html>