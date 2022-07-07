<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>member 로그인 성공</h1>
	 	<!-- getter를 걸어놓아서 pricipal을 가져올 수 있어요 -->
 	<p>principal : <sec:authentication property="principal"/></p><hr/>
 	<p>MemberVO : <sec:authentication property="principal.member"/></p><hr/>
 	<p>principal로 꺼낸 ID : <sec:authentication property="principal.Username"/></p><hr/>
 	<p>사용자의 이름 : <sec:authentication property="principal.member.userName"/></p><hr/>
 	<p>사용자의 아이디 : <sec:authentication property="principal.member.userId"/></p><hr/>
 	<p>사용자의 권한 목록 : <sec:authentication property="principal.member.authList"/></p><hr/>
	
	
	<a href="/customLogout">로그아웃</a>
</body>
</html>