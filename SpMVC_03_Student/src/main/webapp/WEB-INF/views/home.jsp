<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
header {
	background: #CCEEFF;
	margin:0;
	padding: 1rem;
	color: white;
}
header h3 {
	font-size: 2rem;
}

ol {
	list-style: none;
	margin: 0;
	padding: 0;
	display: flex;
	background-color: #33ddff;
}

ol a{
	display: block;
	text-decoration: none;
	padding: 14px 16px;
}

ol a:hover{
	background-color: #ddd;
	color: black;
	border-bottom: 5px solid black;
}
</style>

</head>
<body>

<header>
<h3>학생정보관리</h3>
</header>

<!--  ul tag : un ordered List : 순서가 없는 list, 머릿글이 동그라미 -->
<!--  ol tag : ordered List : 순서가 있는 list, 머릿글이 숫자 -->
<!--  # : 클릭은 가능하나 어디로 이동하지 마라 -->
<ol>
	<li><a href="${rootPath}/student/list">학생리스트</a></li>
	<li><a href="${rootPath}/student/search">학생검색</a></li>
	<li><a href="#">로그인</a></li>
	<li><a href="#">회원가입</a></li>
</ol>
</body>
</html>