<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="searchDiv">
			<select name="search" class="selectCss">
				<option>제목</option>
				<option>내용</option>
			</select> <input type="text" name="content" /> 
			<input type="submit" value="검색" class="btn" />
		</div>
		<table class="table">
			<tr>
				<th>John</th>
				<th>Doe</th>
				<th>john@example.com</th>
			</tr>
			<tr>
				<td>Mary</td>
				<td>Moe</td>
				<td>mary@example.com</td>
			</tr>
			<tr>
				<td>July</td>
				<td>Dooley</td>
				<td>july@example.com</td>
			</tr>

		</table>
		<div class="right">
			<a class="btn">글쓰기</a>
		</div>

		<div class="center">
			<ul class="pagination">
				<!-- disabled가 있으면 마우스 커서가 금지표시로 바뀐다. -->
				<li class=disabled><a href="#" aria-label="Previous"> <span
						aria-hidden="true">«</span>
				</a></li>
				<!-- 현재 페이지 -->
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">»</span>
				</a></li>
			</ul>
		</div>
	</div>
</body>
</html>