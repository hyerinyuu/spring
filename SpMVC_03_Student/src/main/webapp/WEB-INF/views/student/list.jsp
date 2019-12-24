<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>□□□ list.jsp □□□</title>
</head>
<body>
<h3>학생리스트</h3>
<%
/*
Controller로부터 List 데이터를 수신 하면
forEach 명령ㅇ르 이용해서 List를 view에 구현한다.
자바 기본명령어로는 힘들기 때문에
<c:forEach>를 사용해서 구현한다.

items : controller에서 받은 List의 Atrribute(변수) 이름
var : items 리스트의 요소를 하나씩 저장해 받을 dto이름
*/
%>
<c:forEach items="${STD_LIST}" var="std" varStatus="itemNum">
	
	<p>${itemNum.count}, ${itemNum.index } : ${std}

</c:forEach>

</body>
</html>