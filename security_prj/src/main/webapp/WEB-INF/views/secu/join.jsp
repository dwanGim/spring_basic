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
	<h1>회원가입창</h1>
	<form action="/secu/join" method="post">
		아이디 :  <input type="text" name="userId"><br/>
		비밀번호 :  <input type="text" name="userPw"><br/>
		이름 :  <input type="text" name="userName"><br/>
		<input type="checkbox" name="role" value="ROLE_ADMIN">어드민 권한&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="role" value="ROLE_MEMBER">멤버 권한&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="role" value="ROLE_USER">유저 권한<br/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="가입하기">
	</form>

</body>
<script type="text/javascript">

</script>
</html>