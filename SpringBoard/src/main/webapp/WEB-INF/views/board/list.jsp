<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>쓴날짜</th>
				<th>수정날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${board.bno }</td>
					<td><a href="http://localhost:8181/board/detail?bno=${board.bno }">${board.title }</a></td>
					<td>${board.writer }</td>
					<td>${board.regDate }</td>
					<td>${board.updateDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/board/insert"><button class="btn btn-primary">글쓰기</button></a>
</body>
</html>