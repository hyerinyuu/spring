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

.login-form {
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

.login-form h2 {
		
	color: gray;
	font-weight: bold;
	font-size: 36px;
}

.login-form h3 {
		
	color: black;
	font-weight: bold;
	font-size: 20px;
	background-color: red;
	border-radius: 20px;
}


.login-form input {
	
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

.login-form button {
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

.login-form input:focus {
	
	width: 280px;
	border-color: #2ECC71;
	
}

.login-form button:hover{
	background-color: #2ECC71;
	
}
</style>

<script>
	$(function(){
		
		$("#btn-join").click(function(){
			document.location.href="${rootPath}/member/join"
		})
			
		$("btn-login").click(function(){
			
			// 유효성 검사
			// id, password가 입력되지 않았을 때 경고
			let u_id = $("#u_id").val()
			if(u_id == ""){
				alert("아이디를 입력하세요")
				$("#u_id").focus
				return false
			}
			
			/*
			var params = $("form").serialize();
			$.ajax({
				url : "${rootPath}/member/login",
				type : 'POST',
				data: params,
				success:function(result){
					alert("로그인 성공")
				}
			})
			*/

			$.post("${rootPath}/rest/member/login",
				$("form").serialize(),
				function(result){
					alert(result)
					document.location.href=document.location.href
				})/* } : close tag for ==> function(result){  
					 ) : close tag for ==> $.post            */
				
		})/* close tag for ==> $("#btn-login").click(function(){ */
	})
</script>

<form method="POST" action="${rootPath}/member/login" class="login-form">
	<h2>LOGIN</h2>
	
	<c:if test="${LOGIN_MSG == 'FAIL'}">
		<h3>아이디 혹은 비밀번호를 다시 확인하시고 입력해주세요</h3>
	</c:if>
	
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
	
	<input type="text" id="u_id" name="u_id" placeholder="userID">
	<input type="password" id="u_password" name="u_password" placeholder="password">
	<button type="submit" id="btn-login">SIGN UP</button>
	<button type="button" id="btn-join">SIGN IN</button>
	
</form>