<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<c:set var="rootPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<link rel="styleSheet" type="text/css" href="${rootPath}/css/login.css?ver=2019-12-03-02224">
</head>
<body>

<form method="POST" class="login-form">
	<h2>LOGIN</h2>
	
	<c:if test="${LOGIN_MSG == 'TRY'}">
		<h3>login required</h3>
	</c:if>
	
<%
/*
	<c:if test="${LOGIN_MSG == '0'}">
		<h3>WELCOME</h3>
	</c:if>
*/
%>	<c:if test="${LOGIN_MSG == 'NO_AUTH'}">
		<h3>작성자만이 볼 수 있음!!</h3>
	</c:if>
	
	<input type="text" name="u_id" placeholder="userID">
	<input type="password" name="u_password" placeholder="password">
	<button>SIGN UP</button>
	<c:if test="${LOGIN_MSG == '0'}">
		<button>SIGN IN</button>>
	</c:if>
	
	
</form>
	
</body>
</html>