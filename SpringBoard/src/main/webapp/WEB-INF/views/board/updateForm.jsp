<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- insertForm페이지에서 폼양식을 가져온 다음
	얻어온 글에 대한 정보를 끼워넣어주세요. -->
	<form action="/board/update" method="post">
		<input type="text" name="title" value="${board.title }" required/>
		<input type="text" name="writer" value="${board.writer  }" required/>
		<textarea name="content" required>${board.content }</textarea>
		<input type="hidden" name="bno" value="${board.bno }">
		<input type="hidden" name="page" value="${param.page}"/>
		<input type="hidden" name="searchType" value="${param.searchType}"/>
		<input type="hidden" name="keyword" value="${param.keyword}"/>
		<input type="submit">
	</form>
</body>
</html>