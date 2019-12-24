<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>

$(function(){
	$("#s_cat").change(function(){
		$("form").submit()
	})
	/*
	 아직은 모든 브라우저에서 지원하지 않지만
	 type="search"인 inputbox에서 clear 버튼을 눌렀을때
	 발생하는 이벤트를 catch하여 처리가능
	*/
	$("#s_text").on("search",function(){
		$("form").submit()
	})
		
})
	

</script>

<style>
	header {
		background-color: lightgreen;
		color: black;
		text-align: center;
		padding: 0.9rem;
		/* header부분의 글자를 uppercase로 변경*/
		text-transform: uppercase;
	}
	
	nav {
		background-color: green;
		margin:0;
		padding: 0.5rem;
	}
	
	nav select{
		width: 300px;
		paddin: 8px 0.5rem;
		border: 1px solid orange;
		/* 위치 왼쪽에서 95% 센터 50%*/
		background: url('${rootPath}/images/arrow.png') no-repeat 95% 50%;
		background-color: white;
		appearance: none;
		-moz-appearance: none; 
		-webkit-appearance: none;
	}

	nav form, nav option{
		width: 300px;
		position: relative;	
		padding: 16px 14px;
		margin: 10px;
		border: 1px solid orange;
		border-radius: 20px;
			
	}
	
	
	nav #input:hover :after{
		content: '동물병원 이름이나 주소를 입력하고 Enter를 누르세요';
		position: absolute;
		top: 40px;
		left: 30px;
		z-index: 3;
		
	}

</style>

</head>
<body>

<header>
	<h3>My Pet Life</h3>
</header>
<nav>	
<form:form modelAttribute="searchVO" method="GET">
	<form:select path="s_cat" >
		<form:option value="hs">병원명</form:option>
		<form:option value="addr">주소</form:option>
	</form:select>
	<form:input type="search" path="s_text" placeholder="검색어를 입력한후 Enter를 입력하세요"/>
</form:form>
</nav>	
<section class="list">
	<table class="list">
		<tr>
			<th>동물병원의 이름</th>
			<th>도로명주소</th>
			<th>지번주소</th>
			<th>대행업소의 연락처</th>
			<th>위도</th>
			<th>경도</th>
			<th>데이터기준일</th>
		</tr>
		<c:choose>
			<c:when test="${empty H_LIST}">
				<tr><td colspan="7">데이터가 없음</td></tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${H_LIST}" var="hs" varStatus="" >
					<tr>
						<td>${hs.apiDongName}</td>
						<td>${hs.apiNewAddress}</td>
						<td>${hs.apiOldAddress}</td>
						<td>${hs.apiTel}</td>
						<td>${hs.apiLat}</td>
						<td>${hs.apiLng}</td>
						<td>${hs.apiRegDate}</td>
					</tr>	
				</c:forEach>
			</c:otherwise>
		</c:choose>
			
	</table>

</section>

</body>
</html>