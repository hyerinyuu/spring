<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<c:set var="rootPath" value="${pageContext.request.contextPath}"></c:set>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.join-form {
	width: 400px;
	padding: 40px;
	
	background: white;
	text-align: center;
	z-index: 10;
	
	border-radius: 20px;
	border: 2px solid gray;
	box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, 0.2);
	
	margin: 20px auto;
	
}

.join-form h2 {
		
	color: gray;
	font-weight: bold;
	font-size: 36px;
}

.join-form h3 {
		
	color: black;
	font-weight: bold;
	font-size: 20px;
	background-color: red;
	border-radius: 20px;
}


.join-form input {
	
	background: none;
	margin: 10px auto;
	padding: 14px 10px;
	text-align: center;
	width: 200px;
	outline: none;
	color: black;
	border-radius: 25px;
	border: 2px solid #3498db;
	transition: 0.2s;
}

.join-form button {
	border: 2px solid #2ECC71;
	
	padding: 14px 40px;
	margin: 20px auto;
	
	background: none;
	display: block;
	
	outline: none;
	color: gray;
	
	border-radius: 15px;
	
	
	cursor: pointer;
}

.join-form input:focus {
	
	width: 280px;
	border-color: #2ECC71;
	
}

.join-form button:hover{
	background-color: #2ECC71;
	
}
</style>

<form method="POST" action="${rootPath}/member/join" class="join-form">

	<h2>JOIN</h2>
	
	<input type="text" name="u_id" placeholder="userID">
	<input type="password" name="u_password" placeholder="password">
	<input type="password" name="u_repassword" placeholder="password check">
	<input type="text" name="u_name" placeholder="user name">

	<button>JOIN</button>
	
</form>