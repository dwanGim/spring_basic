<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<style>
	.uploadResult {
		width:100%;
		backgroud-color:gray;
	}
	
	.uploadResult ul {
		display: flex;
		flex-flow:row;
		justify-content:center;
		align-items: center;
	}
	
	.uploadResult ul li {
		list-style : none;
		padding : 10px;
		align-content:center;
		text-align:center;
	}
	
	.uploadResult ul li img {
		width: 100px;
	}
	
</style>
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
	
	<div class="row">
		<h3 class="text-primary">첨부파일</h3>
		<div id="uploadResult">
			<ul>
				<!-- 첨부파일이 들어갈 위치 -->
			</ul>
		</div><!-- row -->
	</div>
</body>
</html>