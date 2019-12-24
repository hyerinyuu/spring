<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  anchor : 닻 -->
<!--  다른곳으로 연결하는 linker -->
<!--  hyper text의 꽃 -->
<!--  해당 문자열(네이버검색)을 클릭하면
	  href="" 에 ㅓㅅㄹ정된 곳으로 jump하는 코드 
	  -->
<p><a href="https://search.naver.com/search.naver?query=대한민국">네이버검색1</a>
<p><a href="https://search.naver.com/search.naver?q=대한민국">네이버검색2</a>
<p>네이버검색</p>

<form action="https://search.naver.com/search.naver">

<!--  input box, input tag  -->
	<p><input name="query">
	<p><input name="num1">
	<p><input name="num2">

	<!--  누름단추 -->
	<button>검색</button>
	<output> </output>

</form>

</body>
</html>