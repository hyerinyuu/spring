<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script>
	$(function() {
		
		// js 함수는 1급 함수다. : 함수 = 객체 = 변수
		// 새로운 방식, 함수 = 객체
		// let idcheck = function() {}
		
		// 새창을 열어서 id를 검사하는 코드를 함수로 선언하기
		function idcheck() {

			let top = 500
			/*
			   (window.screen.availTop
			 + (window.screen.availHeight / 2))
			 - (window.screen.height / 2)
			 */
			let left = 500
			/* 
			 ((window.screen.availLeft)
			+ (window.screen.availWidth / 2))
			- (window.screen.width / 2)
			 */

			 
			// alter("Enter입력")
			let u_id = $("#u_id").val()
			let status = "toolbar=no,"
			status += "scrollbars=yes,"
			status += "resizable=no,"
			status += "top=500,"
			status += "left=500,"
			status += "width=700,"
			status += "height=400"

			if (u_id == "") {
				alert("아이디를 입력하세요")
				return false
			}

			window.open("${rootPath}/user/idcheck?u_id=" + u_id, "_blank",
					status)

			// openWin.moveTo(left,top)

		}

		$("#u_id").keypress(function(e) {
			if (e.keyCode == 13) {
				// 표준, 일반적으로 함수를 호출하는 방법
				idcheck()
			}
		})

		/*
		$("#u_id_msg").blur(function(e){
			${"#u_id_msg"}.text("blur")
			${"#u_id_msg"}.css("display","block")
		})
		 */
	
		 // event handler에 함수 목록을 등록하는 절차
		 // 이대는 함수가 마치 객체처럼 등록이 되기 때문에
		 // 함수 이름에 ()fmf sjgwl akfdkdi gksek.
		 // 함수 방식을 call back 함수 등록이다 라고 한다.
		 $("#id_check").click( idcheck)
		 
		$("#btn-save").click(function() {
			let pass = $("#u_password").val()
			if (pass == "") {
				alert("비밀번호를 입력하세요")
				$("#u_password").focus()
				return false
			}

			let re_pass = $("#re_password").val()
			if (re_pass == "") {
				alert("비밀번호를 한번 더 입력해주세요")
				$("#re_password").focus();
				return false
			}

			if (pass != re_pass) {
				alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.\n" + "다시 입력해주세요")
				$("#u_password").val("")
				$("#re_password").val("")
				$("#u_password").focus()
				return false
			}

			let u_name = $("#u_name").val()
			if (u_name == "") {
				alert("이름은 반드시 입력하셔야합니다")
				$("#u_name").focus()
				return false
			}
			$("form").submit()
		})
	})
</script>

<style>
fieldset {
	width: 70%;
	margin: 20px auto;
	border: 2px solid green;
	border-radius: 5px;
}

legend {
	font-weight: bold;
	font-size: 20px;
}

input, textarea {
	display: inline-block;
	width: 90%;
	padding: 8px;
	margin: 5px;
	border-radius: 20px;
}

input:focus, textarear:focus {
	border: 2px solid black;
	outline: none;
}

input:hover {
	background-color: #ddd;
	border: 2px solid pink;
}

.in-error {
	display: inline-block;
	margin-left: 20px;
	font-size: 13px;
	color: red;
}

span#u_id_msg {
	display: none;
}

#u_id {
	width: 70%;
}
</style>
</head>
<body>

	<fieldset>
		<legend>
			<c:if test="${TITLE == null}">회원가입</c:if>
			<c:if test="${TITLE != null}">${TITLE}</c:if>
		</legend>
		<form:form modelAttribute="userDTO" autocomplete="on"
			class="user-form">

			<div class="in-box-border">
				<form:input path="u_id" type="text" class="in-box"
					placeholder="please insert UserId and press Enter" />
				<button type="button" id="id_check">아이디검사</button>
				<br />

				<form:errors path="u_id" class="in-error" />
				<br /> <span id="u_id_msg"></span>
			</div>
			<%
				/*
						input말고 password를 사용할 수도 있으나 여기서는 type을 사용	
					*/
			%>
			<div class="in-box-border">
				<form:input path="u_password" type="password" class="in-box"
					placeholder="Password" />
				<br />
				<form:errors path="u_password" class="in-error" />
			</div>

			<%
				/*
						비밀번호 확인 input box를 표준 html로 작성
						비밀번호 확인은 서버로 데이터를 전송할 필요가 없기 대문에
						또한 form:input으로 작성을 하게 되면
						DTO에 해당 필드변수를 작성해야 하는 불편이 있기 때문에
						이 항목에 입력된 값은 
					*/
			%>

			<div class="in-box-border">
				<form:input type="password" path="re_password"
					placeholder="please recheck the Password" />
				<form:errors path="re_password" class="in-error" />
			</div>

			<div class="in-box-border">
				<form:input path="u_name" class="in-box" placeholder="Name" />
				<br />
				<form:errors path="u_name" class="in-error" />
			</div>

			<div class="in-box-border">
				<form:input path="u_nick" class="in-box" placeholder="Nickname" />
				<br />
				<form:errors path="u_nick" class="in-error" />
			</div>

			<div class="in-box-border">
				<form:input path="u_tel" type="number" class="in-box"
					placeholder="Tel" />
				<br />
				<form:errors path="u_tel" class="in-error" />
			</div>

			<button type="button" id="btn-save">저장</button>
		</form:form>
	</fieldset>
</body>
</html>