<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// HelloServlet 클래스의 doGet method에서 
// sendRedirect()로 보내준 데이터를 추출하여 변수에 담음
String strName = request.getParameter("strName");
String strDept = request.getParameter("strDept");
String strGrade = request.getParameter("strGrade");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>학생정보</h3>
	<p>이름 : <%=strName %></p>
	<p>학과 : <%=strDept %></p>
	<p>학년 : <%=strGrade %></p>
</body>
</html>