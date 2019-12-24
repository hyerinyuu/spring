<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function(){
		
		
	})

</script>
</head>
<body>
<header>


</header>
<section>
	<div>
		<c:choose>
			<c:when test="${empty BASELIST}">
			<p>데이터를 찾을 수 없음</p>
			</c:when>
			<c:otherwise>
				<c:forEach items="${BASELIST}" var="base">
					<p>주소:${base.addr1}</p>
					<p>동명:${base.addr2}</p>
					<p>전화번호:${base.tel}</p>
					<p><img width="100px" src="${base.firstimage}" alt="정보이미지"></p>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>

</section>
</body>
</html>