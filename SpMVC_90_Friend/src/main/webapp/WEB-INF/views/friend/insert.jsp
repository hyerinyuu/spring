<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script>
	$(function(){
		$("#btn-save").click(function(){
			let f_name = $("#f_name").val()
			let f_tel = $("#f_tel").val()
			
			if(f_name == ""){
				alert("이름을 입력하세요")
				$("#f_name").focus()
				return false
			}
			if(f_tel == ""){
				alert("전화번호를 입력하세요")
				$("#f_tel").focus()
				return false
			}
			$("form").submit()
		})
		
	})
	
	function maxLengthCheck(object){
		if (object.value.length > object.maxLength){
			object.value = object.value.slice(0, object.maxLength);
		}    
	}

</script>
<fieldset class="friend-input-box">
	<form:form modelAttribute="fVO"> 
		<div class="form-group">
			<label for="f_name">이름</label>
			<form:input path="f_name" class="form-control" />
		</div>
		
		<div class="form-group">
			<label for="f_tel">전화번호</label>
			<form:input type="number" maxlength="11" oninput="maxLengthCheck(this)" path="f_tel"  class="form-control" />
		</div>
		
		<div class="form-group">
			<label for="f_addr">주소</label>
			<form:input path="f_addr"  class="form-control" />
		</div>
		
		<div class="form-group">
			<label for="f_hobby">취미</label>
			<form:input path="f_hobby"  class="form-control" />
		</div>
		
		<div class="form-group">
			<label for="f_rel">관계</label>
			<form:input path="f_rel"  class="form-control"/>
		</div>
		
		<div class="in-box">
			<button type="button" class="btn btn-primary" id="btn-save">저장</button>
		</div>
	
	</form:form>
</fieldset>