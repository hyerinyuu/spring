<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// 여기는 JSP파일 내에 Java코드를 작성하는 영역

	int num1 = 30;
	int num2 = 40;

	int sum = num1 + num2;
	System.out.println(sum);
%>
<!DOCTYPE html>
<html>
<!--  
head tag는 페이지에 대한 정보등을 기록하는 부분 
웹페이지에 표시되지는 않지만 웹페이지에 어떤 것을 표시하기 위한 다양한 설정등을 기록하는 부분
-->

<head>
	<meta charset="UTF-8">
	<!--  title => web페이지를 표시할 때 제목으로 사용되는 문자열 -->
	<title>나의 첫번째 홈페이지</title>
</head>

<!--  웹페이지에 나타날 정보들을 표시하는 영역 -->
<!--  디자인과 관련된 문서표시를 하는 부분 -->
<body>
	<!--  h tag는 h1 ~ h6까지 사용되며 본문보다 큰 글자를 표시 -->
	<h2>나는 Home.jsp입니다</h2>
	<h3>반갑습니다</h3>
	<h4>우리나라 만세</h4>
	<h4><%=sum%></h4>
	<p>paragraph</p>
	<p>문단tag</p>
	<p>본문tag</p>
	<p>웹페이지에서는 Enter의 의미가 없다.</p>
	<p>한개의 문단내의 문자열이 매우 커서 한줄에 표시가 되지 않을 때는 
	자동으로 다음줄로 넘겨져서 표시가 된다.
	이러한 현상을 Auto Word Wrap이라고 한다.</p>
	<p>문서를 작성하면서 Enter키를 입력해도
	웹페이지에서는 단지 1개의 빈칸으로 인식한다.
	여러줄에 걸쳐 문서를 작성하기 위해 Enter를 입력하지만
	실제 웹페이지에서 볼때는 단지 1개의 빈칸으로만 인식한다.</p>
	<p>문자열을 표시하면서 br tag를 사용하면 해당 위치에서 강제 줄바꿈이 된다.
	br tag는 임의로 문단내에서 문자열을 여러줄에 표시하고자 할 때 사용한다.<br/>
	break의 약자</p>
	<p> br tag로 줄바꿈을 하면 문단내에서는<br/> 
	위아래 여백이 없는 상태로 문자열들이 표시된다.<br/>
	
	<p>우리나라<br/>
	대한민국<br/>
	Republic of Korea</p>
	
	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac.
Senectus et netus et malesuada. Nunc pulvinar sapien et ligula ullamcorper malesuada proin. Neque convallis a cras semper auctor. Libero id faucibus nisl tincidunt eget. Leo a diam sollicitudin tempor id. A lacus vestibulum sed arcu non odio euismod lacinia. In tellus integer feugiat scelerisque. Feugiat in fermentum posuere urna nec tincidunt praesent. Porttitor rhoncus dolor purus non enim praesent elementum facilisis. Nisi scelerisque eu ultrices vitae auctor eu augue ut lectus. Ipsum faucibus vitae aliquet nec ullamcorper sit amet risus. Et malesuada fames ac turpis egestas sed. Sit amet nisl suscipit adipiscing bibendum est ultricies. Arcu ac tortor dignissim convallis aenean et tortor at. Pretium viverra suspendisse potenti nullam ac tortor vitae purus. Eros donec ac odio tempor orci dapibus ultrices. Elementum nibh tellus molestie nunc. Et magnis dis parturient montes nascetur. Est placerat in egestas erat imperdiet. Consequat interdum varius sit amet mattis vulputate enim.
Sit amet nulla facilisi morbi tempus. Nulla facilisi cras fermentum odio eu. Etiam erat velit scelerisque in dictum non consectetur a erat. Enim nulla aliquet porttitor lacus luctus accumsan tortor posuere. Ut sem nulla pharetra diam. Fames ac turpis egestas maecenas. Bibendum neque egestas congue quisque egestas diam. Laoreet id donec ultrices tincidunt arcu non sodales neque. Eget felis eget nunc lobortis mattis aliquam faucibus purus. Faucibus interdum posuere lorem ipsum dolor sit.</p>
	<p>국무회의는 정부의 권한에 속하는 중요한 정책을 심의한다. 국회의 정기회는 법률이 정하는 바에 의하여 매년 1회 집회되며, 국회의 임시회는 대통령 또는 국회재적의원 4분의 1 이상의 요구에 의하여 집회된다. 국가원로자문회의의 조직·직무범위 기타 필요한 사항은 법률로 정한다. 모든 국민은 법 앞에 평등하다. 누구든지 성별·종교 또는 사회적 신분에 의하여 정치적·경제적·사회적·문화적 생활의 모든 영역에 있어서 차별을 받지 아니한다.

국가는 법률이 정하는 바에 의하여 재외국민을 보호할 의무를 진다. 헌법재판소 재판관의 임기는 6년으로 하며, 법률이 정하는 바에 의하여 연임할 수 있다. 공무원인 근로자는 법률이 정하는 자에 한하여 단결권·단체교섭권 및 단체행동권을 가진다. 국회는 국무총리 또는 국무위원의 해임을 대통령에게 건의할 수 있다.

이 헌법은 1988년 2월 25일부터 시행한다. 다만, 이 헌법을 시행하기 위하여 필요한 법률의 제정·개정과 이 헌법에 의한 대통령 및 국회의원의 선거 기타 이 헌법시행에 관한 준비는 이 헌법시행 전에 할 수 있다. 형사피해자는 법률이 정하는 바에 의하여 당해 사건의 재판절차에서 진술할 수 있다. 대통령은 국회에 출석하여 발언하거나 서한으로 의견을 표시할 수 있다.</p>

</body>
</html>