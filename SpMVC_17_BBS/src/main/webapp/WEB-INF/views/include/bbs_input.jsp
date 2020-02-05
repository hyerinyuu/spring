<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script>
	$(function(){
		$("button.btn-save").click(function(){
			let bbs_writer = $("#bbs_writer").val()
			let bbs_subject = $("#bbs_subject").val()
			let bbs_content = $("#bbs_content").val()
			
			if(bbs_writer == ""){
				alert("작성자는 반드시 입력하셔야합니다.")
				$("#bbs_writer").focus()
				return false
			}
			
			if(bbs_subject == ""){
				alert("제목을 입력하세요")
				$("#bbs_subject").focus()
				return false
			}
			
			if(bbs_content == ""){
				alert("내용을 입력하세요")
				$("#bbs_content").focus()
				return false
			}
			
			$("form").submit()
			
		})

	})

</script>

<form:form action="${rootPath}/bbs/save" modelAttribute="bbsVO">
	<div class="form-group">
		<form:input class="form-control" path="bbs_date" placeholder="날짜"/>
	</div>
	
	<div class="form-group">
		<form:input class="form-control" path="bbs_time" placeholder="시간"/>
	</div>
	
	<div class="form-group">
		<form:input class="form-control" path="bbs_writer" placeholder="작성자"/>
	</div>
	
	<div class="form-group">
		<form:input class="form-control" path="bbs_subject" placeholder="제목"/>
	</div>
	
	<div class="form-group">
		<form:textarea class="form-control" path="bbs_content" placeholder="내용"/>
	</div>
	
	<div class="form-group">
		<button type="button" class="btn btn-primary btn-save">저장</button>
	</div>
	
</form:form>
