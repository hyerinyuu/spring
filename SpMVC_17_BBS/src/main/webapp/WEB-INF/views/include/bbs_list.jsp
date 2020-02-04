<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	#datanotfound{
		text-align: center;
	}
	
	.bbslist{
		margin-top: 2rem;
	}
</style>

<script>
$(function(){
	
	$(".bbs-list").click(function(){
		document.location.href="${rootPath}/bbs/view/" + $(this).data("bbsid")
	})
})
	

</script>
<article class="container-fluid bbslist">
	<table class="table table-striped table-hover">
		<thead class="thead-dark">
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>작성일자</th>
				<th>작성시각</th>
				<th>작성자</th>
			</tr>
		</thead>
		<c:choose> 
			<c:when test="${empty BBSLIST}">
				<tr><td id="datanotfound" colspan="5">데이터가 없습니다</td></tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="BBS" items="${BBSLIST}" varStatus="index">
					<tr class="content-body bbs-list" data-bbsid="${BBS.bbs_id}">	
						<td>${index.count}</td>
						<td>${BBS.bbs_subject}</td>
						<td>${BBS.bbs_date}</td>
						<td>${BBS.bbs_time}</td>
						<td>${BBS.bbs_writer}</td>
					</tr>
					<c:if test="${not empty BBS.bbs_reply}">
						<c:set var="BBS_SUB" value="${BBS.bbs_reply}" />
						<%@ include file="/WEB-INF/views/include/bbs_reply.jsp" %>
					</c:if>
				</c:forEach>	
			</c:otherwise>		
		</c:choose>
	</table>
</article>