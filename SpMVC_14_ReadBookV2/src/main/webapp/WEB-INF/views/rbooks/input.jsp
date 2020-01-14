<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="${rootPath}/css/rbook-main.css?ver=2020-01-14-001" rel="stylesheet"/>
<link href="${rootPath}/css/main-table.css??ver=2020-01-14-001" rel="stylesheet" />
<link href="${rootPath}/css/color.css??ver=2020-01-14-001" rel="stylesheet" />

<script>
$(function(){
	// ==  $("#btn-write").click(function(){})
	$("#btn-write").click(()=>{
		document.location.href="${rootPath}/rbook/insert"
	})
	
})

</script>

</head>
<body>

<header>
	<h2>MY READ BOOK</h2>
</header>

<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>


<section id="main-writer">
	<article>
		<form:form modelAttribute="rBookVO">
			<form:input path="rb_bcode" type="text" placeholder="도서코드"/><br/>
			<form:input path="rb_date" type="text" placeholder="독서일자"/><br/>
			<form:input path="rb_stime" type="text" placeholder="독서시작시간"/><br/>
			<form:input path="rb_rtime" type="text" placeholder="독서시간"/><br/>		
			<form:input path="rb_subject" type="text" placeholder="한줄평"/><br/>
			<form:input path="rb_star" type="text" placeholder="별점"/><br/>
			<form:textarea path="rb_text" placeholder="독서소감"/><br/>
			
			<div id="main-button">
				<button id="btn-write" class="biz-blue flex-right" >독서록 저장</button>
			</div>
			
		</form:form>
	</article>

	

</section>

</body>

</html>
