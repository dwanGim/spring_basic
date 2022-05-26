<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="http://localhost:8181/getVO" method="post">
			이름 : <input type="text" name="height" placeholder="이름을 적어주세요!"><br/>
			나이 : <input type="number" name="age" placeholder="나이를 적어주세요!"><br/>
			주소 : <input type="text" name="addr" placeholder="주소를 적어주세요!"><br/>
			레벨 : <input type="number" name="level" placeholder="레벨을 적어주세요!"><br/>
			<input type="submit" value="확인">
		</form>
</body>
</html> 