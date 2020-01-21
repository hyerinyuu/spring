<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<script>

$(function(){
	
	$(".email-list").click(function(){
		document.location.href = "${rootPath}/ems/view/" + $(this).data("seq")
		
	})
	
})

</script>

<section>
	<div class="table-view">
		<table class="table table-hover" id="viewtable">
			<thead class="thead-dark">
				<tr>
					<th>NO</th>
					<th>받는 Email</th>
					<th>받는사람</th>
					<th>제목</th>
					<th>작성일자</th>
					<th>작성시각</th>
				</tr>
			</thead>	

			<c:choose>
					<c:when test="${empty LIST}">
						<tr>
							<td colspan="6">데이터가 없음</td>
						</tr>
					</c:when>
					
					<c:otherwise>
						<c:forEach items="${LIST}" var="vo" varStatus="in">
							<tr class="content-body email-list" data-seq="${vo.emsSeq}">
								<td>${in.index}</td>
								<td>${vo.fromEmail}</td>
								<td>${vo.fromName}</td>
								<td>${vo.subject}</td>
								<td>${vo.sendDate}</td>
								<td>${vo.sendTime}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>	
</section>