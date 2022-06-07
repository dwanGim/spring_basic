<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table>
		<thead>
			<th>글번호</th>
			<th>제목</th>
			<th>글쓴시간</th>
			<th>수정시간</th>
		</thead>
		<tbody>
			<c:forEach var="boardList" items="${boardList}">
				<td>${boardList.bno}</td>
				<td>${boardList.title}</td>
				<td>${boardList.regDate}</td>
				<td>${boardList.updateDate}</td>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>