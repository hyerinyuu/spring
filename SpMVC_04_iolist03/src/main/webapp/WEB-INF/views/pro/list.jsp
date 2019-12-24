<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>list.jsp</title>
<%@include file="/WEB-INF/views/include/include-css.jspf" %>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script>
	$(function() {

	})
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-pro-header.jsp"%>
<section>
	<article>
		<p id="insert">
			<a href="${rootPath}/pro/insert"><button>새로등록</button></a>
		</p>
	</article>
	<article>
			<table>

				<tr>
					<th>NO</th>
					<th>상품코드</th>
					<th>상품명</th>
					<th>매입단가</th>
					<th>판매단가</th>
					<th>부가세</th>
				</tr>
				<c:choose>
					<c:when test="${empty PROLIST}">
						<tr>
							<td colspan="6">데이터가 없음</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${PROLIST}" var="dto" varStatus="info">
							<tr class="proContent-body" id="${dto.p_code}">
								<td>${info.count}</td>
								<td>${dto.p_code}</td>
								<td>${dto.p_name}</td>
								<td>${dto.p_iprice}</td>
								<td>${dto.p_oprice}</td>
								<td>${dto.p_vat}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
	</article>
</section>	
</body>
</html>