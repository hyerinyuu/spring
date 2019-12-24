<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>list.jsp</title>
<%@include file="/WEB-INF/views/include/include-css.jspf" %>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function() {

	})
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-pro-header.jsp"%>
<section>
	<article>
		<p id="insert">
			<a href="${rootPath}/pro/insert"><button>새로등록</button></a>
		</p>
	</article>
	<%@ include file="/WEB-INF/views/pro/list-body.jsp" %>
	
</section>	
</body>
</html>