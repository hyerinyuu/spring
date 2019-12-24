<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 

<%
	/*
	
	*/
%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<style>

	fieldset{
	
		width:70%;
		margin: 20px auto;
		border: 2px solid green;
		border-radius: 5px;
	}
	
	legend{
		font-weight: bold;
		font-size: 20px;
	}
	
	input, textarea{
		display: inline-block;
		width: 90%;
		padding: 8px;
		margin: 5px;
		border-radius: 20px;
	}
	
	input:focus, textarear:focus {
	
		border: 2px solid black;
		
	}

</style>
</head>
<body>
<%
	/*
		html의 form은 default method가 GET인데
		form:form tag는 default method가 POST
		그래서 method를 따로 붙이지 않음
		대신 modelAttribute를 붙임
		
		
		[modelAttribute]
		form:input 자체가 DTO가 되어 memoDTO의 값을 필드변수(path)에 실어서 보내면
		알아서 각 필드값과 일치하는 변수가 있으면 변수들의 값을 setting해서 보여줌
		(value도 따로 설정하지 않아도 DTO에 이미 다 담겨있기 때문에 값이 담김)
		controller에서 memoDTO를 보내지 않으면 오류남
				
	*/
%>
<fieldset>
	<legend>메모작성</legend>
<form:form modelAttribute="memoDTO" class="memo-form">
<%
	/*
		html tag의 inputbox와 달리
		name이라는 속성을 사용하지 않고 path라는 속성이 변수 설정 역할을 수행
		input은 반드시 self closing을 해줘야 오류가 안남
		
		placeholder : 박스에 도움말을 보여주고 입력하면 도움말이 사라지게 하는 기능
	*/	
%>
	<form:input path="m_date" class="in-box" placeholder="작성일자"/><br/>
	<form:input path="m_time" class="in-box" placeholder="작성시각"/><br/>
	<form:input path="m_auth" class="in-box" placeholder="id"/><br/>
	<form:input path="m_cat" class="in-box" placeholder="카테고리를 입력하세요"/><br/>
	<form:input path="m_subject" class="in-box" placeholder="제목을 입력하세요"/><br/>
	<form:textarea path="m_text" rows="5"/><br/>
	<button>저장</button>
</form:form>
</fieldset>
</body>
</html>