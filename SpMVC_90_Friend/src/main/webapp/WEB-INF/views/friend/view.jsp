<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.table-view{
		width: 95%;
		margin: 0 auto;
	}
	button {
		margin-left: 10px;
	}

</style>
<script>
$(function(){

	$("#btn-update").click(function(){
		if(confirm("친구 정보를 수정하시겠습니까?"))
		document.location.href = "${rootPath}/friend/update/" + $(this).data("id")
	})
	
	$("#btn-delete").click(function(){
		if(confirm("친구 정보를 삭제 하시겠습니까?"))
		document.location.href = "${rootPath}/friend/delete/" + $(this).data("id")
	})
	
	
})


</script>

<section>
	<div class="table-view">
		<table class="table table-striped">
		 	<thead class="thead-dark" >
				<tr data-seq="${fVO.f_id}">
					<th width="20%">NO</th><td colspan="4">${fVO.f_id}</td>
				</tr>
				
				<tr>
					<th width="20%">이름</th><td colspan="4">${fVO.f_name}</td>
				</tr>
				
				<tr>
					<th width="20%">전화번호</th><td colspan="4">${fVO.f_tel}</td>
				</tr>
				
				<tr>
					<th width="20%">주소</th><td colspan="4">${fVO.f_addr}</td>
				</tr>
				
				<tr>
					<th width="20%">취미</th><td colspan="4">${fVO.f_hobby}</td>
				</tr>
				
				<tr>
					<th width="20%">관계</th><td colspan="4">${fVO.f_rel}</td>
				</tr>
			</thead>
				
		</table>
		
		<div class="d-flex">
			<button class="btn btn-success ml-auto" data-id="${fVO.f_id}" id="btn-update" onclick="location.href='${rootPath}/friend/update'">수정</button>
			<button class="btn btn-success" data-id="${fVO.f_id}" id="btn-delete" onclick="location.href='${rootPath}/friend/delete'">삭제</button>
		</div>	
		
	</div>	
</section>	