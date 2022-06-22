<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!--  css, js, 그림파일 등은 src/main/webapp/resources 폴더 아래에 저장한 다음
	/resources/~경로~ 형식으로 적으면 가져올 수 있습니다.
	이렇게 경로가 자동으로 잡히는 이유는 servlet-context.xml에 설정이 잡혀있기 때문입니다. -->
	<link rel="stylesheet" href="/resources/resttest/modal.css">
	
	
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2>Ajax 테스트</h2>
	
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY <input type="text" name="reply" id="newReply">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<!-- 댓글 달리는 영역 -->
	<ul id="replies">
	
	</ul>
	
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
	
	
	
	<!-- jquery CDN -->
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		
	<script>
		let bno = 1310942;
		
		function getAllList(){
			
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
		
		/////////////////
		/// 글 등록 로직 ///
		////////////////
		$("#replyAddBtn").on("click", function(){
			
			let replyer = $("#newReplyWriter").val();
			let reply = $("#newReply").val();
			
			
			
			$.ajax({
				type : 'post',
				url : '/replies',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				
				dataType : "text",
				data : JSON.stringify({
					bno : bno,
					replyer : replyer,
					reply : reply
				}),
				
				success : function(result) {
					if(result == 'SUCCESS'){
						
						alert("등록 되었습니다.")
						// 댓글이 기입되면 바로 갱신된 자료가 조회되어야 하기 때문에
						// getAllList() 메서드를 최후에 삽입해줍니다.
						getAllList();
						// 댓글을 쓴 후 댓글 창에 기입한 텍스트들이 사라지게 해보겠습니다
						$("#newReplyWriter").val('');
						$("#newReply").val('');	
					}
				}
			
		});
		
			
		}); // 글 등록 로직 END;
		
		///////////////////////
		// 댓글 수정하기 이벤트 연결//
		//////////////////////
		// 이벤트 위윔
		// 1. ul#replies가 이벤트를 걸고 싶은 버튼 전체의 집합이므로 먼저 집단 전체에 이벤트를 겁니다.
		// 2. #replies의 하위 항목 중 최종 목표 태그를 기입해줍니다.
		// 3. 단, 여기서 #replies와 button 사이에 다른 태그가 끼어있다면 경유하는 형식으로 호출해도 됩니다.
		// 4. 콜백함수 내부의 this는 내가 클릭한 button이 됩니다.
		// .attr("태그 내 속성명") => 해당 속성에 부여된 값을 가져옵니다.
			// ex) <li data-rno="21"> => rno에 21을 저장합니다.
	
		$("#replies").on("click", ".replyLi button", function(){
			
			let reply = $(this).parent();
			
			let rno = reply.attr("data-rno");
			
			let replytext = reply.text();
			
			$(".modal-title").html(rno);
			$("#replyText").val(replytext);
			$("#modDiv").show("slow");
			
		});// replies.onclick.replyLibtn, function END
	// 모던 자바스크립트 딥다이브
		
	
	

	</script>
	<!--  delete.js 내부에 코드를 모두 작성한 다음 link 태그를 이용해 인식만 해주세요
	css 파일의 경우 link태그의 href로 경로 지정을 js파일은 script태그의 src로 경로를 지정합니다. -->
	<script src="/resources/resttest/delete.js"></script>
	
	<script src="/resources/resttest/modify.js"></script>
</body>
</html>