<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
						<c:forEach items="${LIST}" var="VO" varStatus="in">
							<tr class="content-body" data-id="${bdto.b_code}">
								<td>${VO.b_code}</td>
								<td>${VO.b_name}</td>
								<td>${VO.b_auther}</td>
								<td>${VO.b_comp}</td>
								<td>${VO.b_year}</td>
								<td>${VO.b_iprice}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>	
</section>