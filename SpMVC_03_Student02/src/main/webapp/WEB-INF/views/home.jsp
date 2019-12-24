<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<style type="text/css">

/* 문서의 모든 시작값을 inital하는 코드 */
*,html,body{
	margin:30;
	padding:1rem;
}

/*
		header : tag이름
		{} : 모양에 대한 여러가지 속성 지정
*/
header {
	background-color: green; /* box의 바탕색을 지정 */
	color : white;  /* header box내의 문자열의 글자색 지정 */
	margin:0;
	padding:1rem;
}
ul{
	/* display : flex == box model tag의 속성을 inline-box 속성으로 변경 */
	/* box model : 화면의 왼쪽부터 화면의 오른쪽 끝까지 꽉 차는 것
	   inline box model : 안에 있는 글자의 크기만큼만 box로 지정됨 
	   float 기법 : 웹페이지의 박스들을 겹겹이 쌓아서 
	   flex 모델 : 박스속성인 litag들은 원래 box모델이나
	   			ul을 flex로 설정해주면 inline으로 자동 변경됨 */
	display: flex;
	list-style: none; /* list의 머릿글 제거하는 속성(dot remove) */
}

li{
	width: 100px;
	margin-right:10px;
	background-color: green;
}


/* 태그만 시작하면 본문에 있는 모든 tag에 적용 */
a {
	text-decoration: none; /* a tag에 적용되어서 밑줄을 없애는 용도 */
	padding : 10px;
}
/*
a : hover 액션을 지정하는데
nav tag 내에있는 a tag에만 액션을 지정하라
*/
nav a:hover{
	font-weight: bold; /* 마우스를 올리면 글자를 bold로 변경 */
	font-style: italic;
}

p {
	background-color: cyan;
}

/* 이문서에 p1이라는 id는 유일한 값이기 때문에 p#p1 이런식으로 지정해주지 않아도 됨 */
#p1{
	font-size : 50pt;
	background-color: pink;
	color: white;
}
/* 이문서에 class가 pc로 지정된 tag에 스타일을 지정하라 */
/* p.pc 또는 .pc 형식으로 지정해도 된다. */
p.pc {
	background-color: skyblue;
	color : white;
	
}
</style>
</head>
<body>
<header>
	<h3>나의 홈페이지</h3>
</header>
<nav>
	<ul>
		<li><a href="#">학생리스트</a></li>
		<li><a href="#">학생검색</a></li>
		<li><a href="#">로그인</a></li>
		<li><a href="#">회원가입</a></li>
	</ul>
</nav>
<section>
	<article>
		<p><font size=30pt color=green face=돋움>여기는 본문부분입니다</font></p>
		<p style="font-size:50pt;color:black;background-color:yellow">여기는 본문2입니다</p>
		<p>여기는 나의 이야기 입니다</p>
		<p id="p1">여기는 p1 입니다</p>
		<p id="p2">여기는 p2 입니다</p>
		<p id="p3" class="pc">여기는 p3 입니다</p>
		<p id="p4" class="pc">여기는 p4 입니다</p>
		<p id="p5" class="pc">여기는 p5 입니다</p>
		<p><a href="https://www.naver.com/">네이버 바로가기</a>
			<a href="https://daum.net/">다음 바로가기</a>
	</article>
</section>
<footer>
	<address>CopyRight &copy; hyerin.you@gmail.com</address>
</footer>
</body>
</html>