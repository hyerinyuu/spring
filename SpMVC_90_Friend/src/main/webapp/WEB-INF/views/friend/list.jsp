<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
 #nodata{
 	text-align: center;
 }
 
 #pg-box{
 	margin: 0 auto;
 }

</style>
<script>

$(function(){
	
	$(".friend-list").click(function(){
		document.location.href = "${rootPath}/friend/view/" + $(this).data("id")
	})
	
})

</script>

<section>
	<div class="table-view">
		<table class="table table-hover" id="list-table">
			<thead class="thead-dark">
				<tr>
					<th>NO</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>취미</th>
					<th>관계</th>
				</tr>
			</thead>	

			<c:choose>
					<c:when test="${empty FLIST}">
						<tr>
							<td colspan="6" id="nodata">데이터가 없음</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${FLIST}" var="fvo" varStatus="in">
							<tr class="content-body friend-list" data-id="${fvo.f_id}">
								<td>${in.index}</td>
								<td>${fvo.f_name}</td>
								<td>${fvo.f_tel}</td>
								<td>${fvo.f_addr}</td>
								<td>${fvo.f_hobby}</td>
								<td>${fvo.f_rel}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<div class="d-flex">
				<button class="btn btn-primary ml-auto" id="btn-send" onclick="location.href='${rootPath}/friend/insert'">주소록 추가</button>
			</div>
			
			<!--  PAGINATION -->
			<div class="container" id="pg-box">
				<ul class="pagination">
				    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
				    <li class="page-item"><a class="page-link" href="#">1</a></li>
				    <li class="page-item"><a class="page-link" href="#">2</a></li>
				    <li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				</ul>
			</div>
	</div>	
</section>