<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${board}
	<br/>
	<a href="/board/list"><button>목록으로 돌아가기</button></a>
	<!-- 삭제용 폼을 만들어주면 됩니다.
	post방식으로 컨트롤러의 delete로직을 호출하면 되고,
	글번호를 bno라는 이름에 담아서 보내주도록 하면 됩니다. -->
	<form action="/board/delete" method="post">
		<input type="hidden" name="bno" value="${board.bno}"/>
		<input type="submit" value="삭제하기">
	</form>
	<form action="/board/updateForm" method="post">
		<input type="hidden" name="bno" value="${board.bno}"/>
		<input type="submit" value="수정하기">
	</form>
</body>
</html>