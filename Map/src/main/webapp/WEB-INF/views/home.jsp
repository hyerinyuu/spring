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
</head>
<header>
	<a href="${rootPath}/"><img src=" ${rootPath}/images/logo.png" width=300 alt="로고이미지"></a>
</header>
<style>
	
	header a{
		
	}

</style>
<body>
	<nav>
		<ul>
			<li><a href="${rootPath}/pet/index">소개</a></li>
			<li><a href="${rootPath}/pet/findhospital">병원찾기</a></li>
			<li><a href="${rootPath}/pet/bloodtest">피검사</a></li>
			<li><a href="${rootPath}/pet/report">상태보고서</a></li>
			<li><a href="${rootPath}/pet/comunity">커뮤니티</a></li>
		</ul>
	</nav>
</body>
</html>