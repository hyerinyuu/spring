<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<p />
<p />
<p />
<style>
table {
	/*
      [border-collapse]
      table을 표시할 때
      table의 바깥쪽 라인과 
      각 칼럼 라인 간의 간격을 없애고자 할 때
            */
	/* border-collapse: collapse; */
	/*
            표를 실선 모양으로 외부 라인 설정
            */
	/*
            border-spacing: 0;
            */
	width: 90%;
	border: 1px solid pink;
	border: 1px dotted pink;
	/* top과 bottom여백을 20px로 하고, left와 right 여백을 자동(좌우중앙에 box를 위치하라)으로 하라 */
	margin: 20px auto;
}

table tr {
	border: 1px dashed red;
}

/* [td, th] : td tag와 th tag에 공통된 속성을 부여하고 싶을 때 */
table td, table th {
	padding: 8px;
	vertical-align: top;
}

table th {
	text-align: left;
}

table tr:nth-child(odd) {background - color:#fff;
	
}

table tr:nth-child(even) {background - color:#ccc;
	
}

/*
table의 row에 마우스가 위치하면 바탕색을 gay로 설정하고
마우스 커서를 손가락 모양으로 변경
*/
table tr:hover {
	background-color: lightgray;
	cursor: pointer;
}
#whenDataisNull {
	text-align: center;
}
</style>
<table>
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>학과</th>
		<th>학년</th>
		<th>전화번호</th>
	</tr>

	<c:choose>
		<c:when test="${STDLIST == NULL}">
			<tr>
				<td id="whenDataisNull"colspan="5">데이터가 없습니다</td>
			</tr>	
		</c:when>
		<c:otherwise>

			<c:forEach items="${STDLIST}" var="dto">
				<tr>
					<td>${dto.st_num}</td>
					<td>${dto.st_name}</td>
					<td>${dto.st_dept}</td>
					<td>${dto.st_grade}</td>
					<td>${dto.st_tel}</td>
				</tr>
			</c:forEach>

		</c:otherwise>
	</c:choose>

</table>
