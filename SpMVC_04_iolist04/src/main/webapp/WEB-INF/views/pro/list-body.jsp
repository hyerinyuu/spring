<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${rootPath}/css/list-table.css?ver=2019-11-31-003">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>
<script>
	$(function(){
		
		$(".proContent-body").click(function(){
			
			// this : tr
			// tr의 자손들을 추출하여 mTD에 저장해달라
			let mTD = $(this).children();
			let strNo = mTD.eq(0).text();
			let strCode = mTD.eq(1).text();
			let strName = mTD.eq(2).text();
			let strIprice = mTD.eq(3).text();
			let strOprice = mTD.eq(4).text();
			
			/* alert(strNo + "\n" + strCode + "\n" + strName + "\n"+ strIprice + "\n"+ strOprice + "\n") */
			
			// inputbox는 val, span은 text로 써야함
			$("#io_pcode",opener.document).val(strCode)
			$("#io_pname",opener.document).text(strName)
			
			let strInout = $("#io_inout", opener.document).val()
			
			// let strInout = $(opener.document).find("#io_inout").val()
			
			if(strInout == "1"){
				$("#io_price",opener.document).val(strIprice)
			}else {
				$("#io_price",opener.document).val(strOprice)
			}
			
			// 수량 * 단가 = 합계 계산하여 붙여넣기
			let price = $("#io_price",opener.document).val()
			let qty = $("#io_price",opener.document).val()
			$("#io_total", opener.document).val(
				parseInt(price) * parseInt(qty)
					
			)
			
			
			window.close()
			// internet exploler때문에 쓸데없이 쓰는 코드
			// ie는 창을 닫을까요라고 물어봐서 안물어보고 바로 창을 닫을 수 있게 하는 코드
			// 쓸데없는 창을 하나 띄워주고 같이 닫아주는 코드
			window.open("about:blank", "_self").self.close()
			
		})
		
	})

</script>

<body>
<style>
	div.s-box {
		width: 95%;
		margin: 0 auto;
	}
	div.s-box input{
		padding: 8px;
		width: 100%;
		
	}

</style>
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
</body>
</html>