<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
$(function() {
	//목록 버튼 클릭 시
	$("#list").click(function(){
		$(location).attr("href","${pageContext.request.contextPath}/order/list/corp.com")
	});
	//검색
	$("#searb").click(function() {
		var form = $("#searchF").serialize();
		$.ajax({
			url : "${pageContext.request.contextPath}/order/list/corp.com",
			type : 'post',
			data : form,
			success : function(data) {
				alert(form);
				location.href = "${pageContext.request.contextPath}/order/list/corp.com?"+ form;
			},
			error : function(request,status,error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="searchDiv">
			<!-- 검색전 -->
			<c:if test="${empty param.start && empty param.last}">
				<form id="searchF">
					<input type="date" name="start" /> ~ <input type="date"
						name="last" />
					<input type="button" id="searb" value="검색"/>
					<input type="button" id="list" value="전체보기"/>
				</form>
			</c:if>
			<!-- 검색후 -->
			<c:if test="${!empty param.start || !empty param.last}">
				<form id="searchF">
				<input type="date" name="start" value="${param.start}"/> ~ <input type="date"
						name="last" value="${param.last}"/>
				<input type="button" id="searb" value="검색"/>
				<input type="button" id="list" value="전체보기"/>
				</form>
			</c:if>
		</div>

		<table class="table">
			<tr>
				<th>주문상세번호</th>
				<th>상품카테고리</th>
				<th width="35%">상품명</th>
				<th>주문현황</th>
				<th>고객 ID</th>
				<th>주문날짜</th>
			</tr>
			<c:if test="${ empty ORDER.oddto}">
			<tr>
				<td colspan="6" style="text-align: center;">주문이 없습니다.</td>
			</tr>
			</c:if>
			<c:forEach items="${ORDER.oddto}" var="oddto">
				<c:forEach items="${ORDER.sdto}" var="sdto">
					<tr>
						<c:if test="${oddto.ino eq sdto.ino}">
							<td>${oddto.odno}</td>
							<td>${sdto.icategory}</td>
							<td><a
								href="${pageContext.request.contextPath}/order/detail/corp.com?no=${oddto.odno}&nowPage=${PINFO.nowPage}">${sdto.iname}</a>
							</td>
							<td>${oddto.ostatus}</td>
							<c:if test="${oddto.ono eq ORDER.odto1.ono}">
								<td>${ORDER.odto1.mid}</td>
								<td>${ORDER.odto1.odate}</td>
							</c:if>
						</c:if>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>

		<div class="center">
			<ul class="pagination">
				<li><c:if test="${PINFO.nowPage > 3}">
						<a
							href="${pageContext.request.contextPath}/order/list/corp.com?nowPage=${PINFO.nowPage-3}">«</a>
					</c:if> <c:if test="${PINFO.nowPage <= 3}">
						<a
							href="${pageContext.request.contextPath}/order/list/corp.com?nowPage=1">«</a>
					</c:if></li>
				<!-- 현재 페이지일때 active -->
				<c:forEach begin="${PINFO.startPage}" end="${PINFO.endPage}" var="i">
					<li id="li">
						<!-- 스크립트 적용해야 할것같아요 --> <a
						href="${pageContext.request.contextPath}/order/list/corp.com?nowPage=${i}">${i}</a>
					</li>
				</c:forEach>
				<li><c:if test="${PINFO.endPage-PINFO.nowPage>=2}">
						<a
							href="${pageContext.request.contextPath}/order/list/corp.com?nowPage=${PINFO.endPage+1}">»</a>
					</c:if> <c:if test="${PINFO.endPage-PINFO.nowPage<2}">
						<a
							href="${pageContext.request.contextPath}/order/list/corp.com?nowPage=${PINFO.endPage}">»</a>
					</c:if></li>
			</ul>
		</div>
	</div>
</body>
</html>