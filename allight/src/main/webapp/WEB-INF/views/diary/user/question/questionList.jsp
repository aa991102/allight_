<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){//안되요ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
		$('#li').click(function(){
			$('#li').addClass("active");
		});
	})
</script>
</head>
<body>
	<div class="container">
		<table class="table">
			<tr>
				<th>NO</th>
				<th width="70%">제목</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${LIST}" var="list">
				<tr>
					<td>${list.qno}</td>
					<td><a href="${pageContext.request.contextPath}/question/detail.com?no=${list.qno}&nowPage=${PINFO.nowPage}">${list.qtitle}</a></td>
					<td>${list.qdate}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="right">
			<a class="btn" href="${pageContext.request.contextPath}/question/write.com">글쓰기</a>
		</div>
		<div class="center">
			<ul class="pagination">
				<li>
					<c:if test="${PINFO.nowPage > 3}">
						<a href="${pageContext.request.contextPath}/question/list.com?nowPage=${PINFO.nowPage-3}">«</a>
					</c:if>
					<c:if test="${PINFO.nowPage <= 3}">
						<a href="${pageContext.request.contextPath}/question/list.com?nowPage=1">«</a>
					</c:if>
				</li>
				<!-- 현재 페이지일때 active --> 
				<c:forEach begin="${PINFO.startPage}" end="${PINFO.endPage}" var="i">
					<li id="li"><!-- 스크립트 적용해야 할것같아요 -->
						<a href="${pageContext.request.contextPath}/question/list.com?nowPage=${i}">${i}</a>
					</li>
				</c:forEach>				
				<li>
					<c:if test="${PINFO.nowPage < PINFO.endPage-3}">
						<a href="${pageContext.request.contextPath}/question/list.com?nowPage=${PINFO.nowPage+3}">»</a>
					</c:if>
					<c:if test="${PINFO.nowPage >= PINFO.endPage-2}">
						<a href="${pageContext.request.contextPath}/question/list.com?nowPage=${PINFO.endPage}">»</a>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>