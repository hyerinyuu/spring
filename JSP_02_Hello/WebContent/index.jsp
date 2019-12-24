<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	[index.jsp]
	보통 도메인네임을 브라우저에 입력하고 Enter를 눌렀을 대
	최초로 보여지는 화면을 index화면이라고 하고
	이때 index화면을 구현한 파일들이 있다.
	index.html, index.htm, => 단순서버 (static방식으로 구현된 서버에서)
	index.php, index.asp, indes.jsp => Dynamic방식으로 구현된 서버
	index page => 화면구현에서 최초로 만들게 되는 화면
	
	static 방식 : 문서 파일 형태로 화면을 구현하고, 누구에게나 거의 유사한 
				화면을 보여주는 방식
	dynamic 방식 : DB, App과 연동되어서 사용자의 요구사항에 따라
				화면을 다르게 보여주는 능동적인 방식
	
	
*/

String strName = request.getParameter("strName");
	

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>나의 홈페이지</h3>
<p>나(<%=strName %>)의 홈페이지에 오신것을 환영합니다.</p>
<p><a href="https://naver.com/">네이버</a></p>
<p><a href="https://daum.net/">다음</a></p>
<p><a href="https://google.com/">구글</a></p>
<!--  strName query로 전달받은 값을 page.jsp에게 토스하라 -->
<p><a href="http://localhost:8080/JSP_02_Hello/page.jsp?strName=<%=strName%>">나의정보</a></p>
<p><a href="/JSP_02_Hello/page.jsp?strName=<%=strName%>">나의정보</a></p>
<p><a href="/JSP_02_Hello/mypage/mypage1.jsp?strName=성춘향&num1=40&num2=50">성춘향의 정보</a></p>



</body>
</html>