<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- jquery CDN -->
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/resources/resttest/modal.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${board}
	<br/>
	<a href="/board/list?page=${param.page}&searchType=${param.searchType}&keyword=${param.keyword}"><button>목록으로 돌아가기</button></a>
	<!-- 삭제용 폼을 만들어주면 됩니다.
	post방식으로 컨트롤러의 delete로직을 호출하면 되고,
	글번호를 bno라는 이름에 담아서 보내주도록 하면 됩니다. -->
	<form action="/board/delete" method="post">
		<input type="hidden" name="bno" value="${board.bno}"/>
		<input type="hidden" name="page" value="${param.page}"/>
		<input type="hidden" name="searchType" value="${param.searchType}"/>
		<input type="hidden" name="keyword" value="${param.keyword}"/>
		<input type="submit" value="삭제하기">
	</form>
	<form action="/board/updateForm" method="post">
		<input type="hidden" name="bno" value="${board.bno}"/>
		<input type="hidden" name="page" value="${param.page}"/>
		<input type="hidden" name="searchType" value="${param.searchType}"/>
		<input type="hidden" name="keyword" value="${param.keyword}"/>
		<input type="submit" value="수정하기">
	</form>
	
		<!-- 댓글 달리는 영역 -->
	<ul id="replies">
	
	</ul>
	
	<!-- 댓글 모달 -->
	<div id="modDiv" style="display:none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replyText">
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">Delete</button>
			<button type="button" id="closeBtn">Close</button>
		</div>
	</div>
	
	
	<script>
	
		function getAllList(){
			
			let bno = ${board.bno};	
			let str = "";
			
			// json 데이터를 얻어오는 로직 실행
			$.getJSON("/replies/all/" + bno, function(data){
				$(data).each(
					function(){
						console.log(this);
						// 백틱 문자열 사이에 변수를 넣고 싶다면 \${변수명} 을 적습니다.
						// 원래는 \를 왼쪽에 붙일 필요는 없지만
						// jsp에서는 el표기문법이랑 겹치기 때문에 el이 아님을 보여주기 위해
						// 추가로 왼쪽에 \를 붙입니다.
						str+=
						`<li data-rno='\${this.rno}' class='replyLi'>\${this.reply}<button>수정/삭제</button></li>`;
						});
						
				console.log(str);
				$("#replies").html(str);
			});			
		}
		getAllList();
		
		$("#replies").on("click", ".replyLi button", function(){
			
			let reply = $(this).parent();
			
			let rno = reply.attr("data-rno");
			
			let replytext = reply.text();
			
			$(".modal-title").html(rno);
			$("#replyText").val(replytext);
			$("#modDiv").show("slow");
			
		});// replies.onclick.replyLibtn, function END

	</script>
	
	<script src="/resources/resttest/delete.js"></script>
	
	<script src="/resources/resttest/modify.js"></script>
</body>
</html>