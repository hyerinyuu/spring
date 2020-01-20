<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/views/include/include-header.jspf" %>

<style>
.email-write-box{
		width: 60%;
		margin: 10px auto;
		border-radius: 4px;
	}
	
	div.in-box{
		display: flex;
		margin: 3px;
	}
	
	div.in-box label{
		display: block;
		width: 25%;;
		text-align: right;
		margin: 8px;
		padding: 5px;
		font-weight: bold;
		color: blue;	
		white-space: nowrap;	
	}

	div.in-box input{
		width: 60%;
		border: 1px solid #aaa;
		border-radius: 4px;
		padding: 5px;
		margin: 3px;
	}
	
	div.in-box input:focus{
		background-color: lightblue;
	}
</style>

<script>

$(function(){
	
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
	
	$("#content").summernote({
		lang: 'ko-KR',
		placeholder: '본문을 입력하세요',
		width: '100%',
		toolbar: toolbar,
		height: '200px',
		disableDragAndDrop: false,
	})
	
})

</script>

<header>
	<div class="jumbotron text-center" style="margin-bottom:0">
  		<h1>MY EMS</h1>
	</div>
</header>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<fieldset class="email-write-box">
	<form:form modelAttribute="emailVO"> <!--  enctype="multipart/form/data" -->
		
		<div class="form-group">
			<label for="from_email">보내는 Email</label>
			<form:input path="from_email" class="form-control" />
		</div>
		
		<div class="form-group">
			<label for="to_email">받는 Email</label>
			<form:input path="to_email"  class="form-control" />
		</div>
		
		<div class="form-group">
			<label for="send_date">작성일자</label>
			<form:input path="send_date"  class="form-control" />
		</div>
		
		<div class="form-group">
			<label for="send_time">작성시각</label>
			<form:input path="send_time"  class="form-control" />
		</div>
		
		<div class="form-group">
			<label for="from_name">작성자</label>
			<form:input path="from_name"  class="form-control"/>
		</div>
		
		<div class="form-group">
			<label for="to_name">받는사람</label>
			<form:input path="to_name"  class="form-control" />
		</div>
		
		<div class="form-group">
			<label for="subject">제목</label>
			<form:input path="subject"  class="form-control" />
		</div>
		
		<div class="in-box">
			<form:textarea path="content" />
		</div>
		
		<div class="in-box">
			<button class="btn btn-primary">메일보내기</button>
		</div>
	
	</form:form>
</fieldset>