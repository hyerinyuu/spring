<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// java script let 코드(javaScript랑 전혀 무관) == java코드지만 Class와 method의 개념 없이 작성(.jsp에서만 가능)
	// 마치 하나의 method와 같은 역할을 함
	String strName = request.getParameter("strName");
	String strNum1 = request.getParameter("num1");
	String strNum2 = request.getParameter("num2");
	
	int intNum1 = 0;
	int intNum2 = 0;
	try{
		intNum1 = Integer.valueOf(strNum1);
		intNum2 = Integer.valueOf(strNum2);
	} catch(Exception e){
		
	}
	int intSum = intNum1 + intNum2;
	
%>

<!--  
html 주석 코드
주석코드를 사용할 때 꺽쇠 태그 사용 금지
본문 코드와 겹쳐서 Web에 보여질 때 의도하지 않은 모양으로 나타날 가능성 큼

느낌표도 가급적 사용 금지 
-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="">

	<p><label>num1</label><input name="num1">
	<p><label>num2</label><input name="num2">
	<p><button>계산</button>
</form>

<h3><%=intSum %></h3>

</body>
</html>