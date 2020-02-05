<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!--  summernote -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote.min.js"></script>
<script src="${rootPath}/js/summernote-ko-KR.min.js"></script>
<style>
	header {
		width: 95%;
		border-radius: 8px;
		margin: 0 auto;
		margin-top: 2rem;
	}
	
	#btn-write{
		color: white;
	}
	
	#navigation-bar{
		border-radius: 5px;
	}
</style>

<script>
	$(function() {

		var toolbar = [
			['style',['bold', 'italic', 'underline'] ],
			['fontsize',['fontsize']],
			['font Style',['fontname']],
			['color', ['color']],
			['para',['ul','ol','paragragh']],
			['height',['height']],
			['table', ['table']],
			['insert', ['link','hr', 'picture']],
			['view', ['fullscreen', 'codeview']]
			
			]
		
		$("#btn-write").click(function() {
			document.location.href = "${rootPath}/bbs/input"
		})

		$("#bbs_content").summernote({
			lang: "ko-KR",
			height: '200px',
			toolbar: toolbar,
			disableDragAndDrop: false
			
		})
		
		
	})
</script>

</head>

<header class="jumbotron text-center">
	<h3>Build Board System</h3>
</header>


<div id="navigation-bar">	
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<ul class="navbar-nav">
			<li class="nav-item active">
				<a class="nav-link" href="${rootPath}/">Home</a>
			</li>
			<li class="nav-item active justify-content-end">
				<a class="nav-link" href="${rootPath}/member/login">Login</a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="${rootPath}/member/join">Join</a>
			</li>
		</ul>
	</nav>
</div>		

<body class="container-fluid">

	<section>
		<c:choose>
			<c:when test="${BODY == 'BBS_INPUT'}">
				<%@ include file="/WEB-INF/views/include/bbs_input.jsp"%>
			</c:when>
			<c:when test="${BODY == 'BBS_VIEW'}">
				<%@ include file="/WEB-INF/views/include/bbs_view.jsp"%>
			</c:when>
			<c:otherwise>
				<%@ include file="/WEB-INF/views/include/bbs_list.jsp"%>
				<div class="input-group">
					<div class="btn btn-primary ml-auto">
						<button class="btn btn-default" id="btn-write" type="button">게시판작성</button>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</section>


</body>
</html>