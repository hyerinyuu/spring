<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>나의 Spring Home</h3>
<P>서버 시간 : ${serverTime}. </P>

<h3>학생정보</h3>
<p>이름 : ${strName}</p>
<p>학과 : ${strDept}</p>
<p>학년 : ${strGrade}</p>

<form action="my" method="POST">
	<input name="st_name">
	<input name="st_dept">
	<button>전송</button>	
</form>
</body>
</html>