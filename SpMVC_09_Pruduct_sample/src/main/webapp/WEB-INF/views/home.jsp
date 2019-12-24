<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
	p{
		border: 1px solid blue;
		padding: 16px;
		margin: 10px;
	}
</style>

<script>
$(function(){
	
	$("#ajax").click(function(){
		$.ajax({
			url:"${rootPath}/nation",
			data : {str:$("#input-1").val()},
			success : function(result){
				$("#p4").text(result)
			}
		})
		
	})
	
})


</script>
</head>
<body>
<header>
	<h2>Product</h2>
</header>
<section>
	<p class="pc" id="p1">click1</p>
	<p><input id="input-1" name="in_01"/>
	<p class="pc" id="ajax">Ajax</p>
	<p class="pc" id="p3">click3</p>
	<p class="pc" id="p4">click4</p>
	<p class="pc" id="p5">click5</p>
</section>

</body>
</html>