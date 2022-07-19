<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- jquery CDN -->
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">	
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/resources/resttest/modal.css">
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
	
		

	<!-- 댓글달리는 영역 -->
	<div class="row">
		<h3 class="text-primary">댓글</h3>
		<div id="replies">
			<!-- 댓글이 들어갈 위치 -->
		</div>
	</div><!-- /.row -->
	
	<!-- 댓글쓰기 -->
	<div class = "row box-box-success">
		<div class="box-header">
			<h2 class="text-primary">댓글 작성</h2>
		</div><!-- header -->
		<div class="box-body">
			<strong>Writer</strong>
			<input type="text" id="newReplyer" placeholder="Replyer" class="form-control">
			<strong>ReplyText</strong>
			<input type="text" id="newReplyText" placeholder="ReplyText" class="form-control">
		</div><!-- body -->
		<div class="box-footer">
			<button type="button" class="btn btn-success" id="replyAddBtn">Add Reply</button>
		</div><!-- footer -->
	</div>	
	
	<!-- 모달창 -->
	<div id="modDiv" style="display:none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replyText">
		</div>
		<div>
			<button type="button" id="replyModBtn">수정하기</button>
			<button type="button" id="replyDelBtn">삭제하기</button>
			<button type="button" id="closeBtn">닫기</button>
		</div>
	</div>
	
	<!-- 파일 업로드 영역 -->
	<div class="row">
		<h3 class="text-primary">첨부파일</h3>
		<div id="uploadResult">
			<ul>
				<!-- 여기에 첨부파일이 들어갑니다. -->
			</ul>
		</div>
	</div>
	
	<script>
	//해당 글 글번호
	let bno = ${board.bno};
	let csrfHeaderName = ${_csrf.headerName};
	let csrfTokenValue = ${csrf.token};

	// 댓글 전체 불러오기
	
					// 백틱 문자열 사이에 변수를 넣고 싶다면 \${변수명} 을 적습니다.
					// 원래는 \를 왼쪽에 붙일 필요는 없지만
					// jsp에서는 el표기문법이랑 겹치기 때문에 el이 아님을 보여주기 위해
					// 추가로 왼쪽에 \를 붙입니다.	
					
					
					
	function getAllList(){
		let str = "";
		// json 데이터를 얻어오는 로직 실행
		$.getJSON("/replies/all/" + bno, function(data){
			$(data).each(
				function(){

					// UNIX 시간을 우리가 알고 있는 형식으로 바꿔보겠습니다.
					let timestamp = this.updateDate;
					// UNIX시간이 저장된 timestamp를 Date 생성자로 변환합니다.
					let date = new Date(timestamp);
					// 변수 formateedTime에 변환된 시간을 저장해 출력해보겠습니다. 
					let formattedTime = `게시일 : \${date.getFullYear()}년
												\${(date.getMonth()+1)}월
												\${date.getDate()}일`;
		str+=
		`<div class='replyLi' data-rno='\${this.rno}'>
			<strong>@\${this.replyer}</strong> - \${formattedTime}<br/>
			<div class='replytext'>\${this.reply}</div>
			<button type='button' class='btn btn-info'>수정/삭제</button>
		 </div>`;
			});
			console.log(str);
			$("#replies").html(str);
		});			
	}
	getAllList();		
	
	///////////////////////////////
	// 댓글수정하기 이벤트 연결
	///////////////////////////////				
	// 이벤트 위임
	
	// 1. ul#replies가 이벤트를 걸고 싶은 버튼 전체의 집합이므로 먼저 집합 전체에 이벤트를겁니다.
	// 2. #replies의 하위 항목중 최종 목표 태그를 기입해줍니다.
	// 3. 단, 여기서 #replies와 button 사이에 다른 태그가 끼어있다면 경유하는 형식으로 호출해도 됩니다.
	$('#replies').on("click", ".replyLi button", function(){
		// 4. 콜백함수 내부의 this는 내가 클릭한 button이 됩니다.
		// 선택 요소와 연관된 태그 고르기
		// 1. prev().prev()... 등과 같이 연쇄적으로 prev, next를 걸어서 고르기
		// 2. prev("태그선택자") 를 써서 뒤쪽이나 앞쪽 형제 중 조건에 맞는것만 선택
		// 3. siblings("태그선택자")는 next, prev 모두를 범위로 조회합니다.
		
		
		let reply = $(this).siblings(".replytext");
		
		// .attr("태그 내 속성명") => 해당 속성에 부여된 값을 가져옵니다.
		// ex) <li data-rno="33"> => rno에 33을 저장해줍니다.
		let rno = reply.parent().attr("data-rno");
		let replytext = reply.text();
		
		$(".modal-title").html(rno);
		$("#replyText").val(replytext);
		$("#modDiv").show('slow');
	});// 댓글 모달 show
	
	$.getJSON("/board/getAttachList", {bno : bno}, function(arr){
		
		console.log(arr);
		
		let str = "";
		
		$(arr).each(function(i, attach){
			// image일 때
			if(attach.fileType) {
				let fileCallPath = encodeURIComponent(attach.uploadPath +"/s_" +
							attach.uuid + "_" + attach.fileName);
				
				str += `<li data-path=\${attach.uploadPath} data-uuid=\${attach.uuid}
						data-filename=\${attach.fileName} data-type=\${attach.fileType}>
						<div>
							<img src='/display?fileName=\${fileCallPath}'>
						</div>
						</li>`;
			} else {
				str += `<li data-path=\${attach.uploadPath} data-uuid=\${attach.uuid}
					data-filename=\${attach.fileName} data-type=\${attach.fileType}>
					<div>
						<span>\${attach.fileName}</span><br/>
						<img src='/resources/fileThumbnail.png' width='100px' height='100px'>
					</div>
					</li>`
			}
		});
		$("#uploadResult ul").html(str);
	});// getJSON END
	
	$("#uploadResult").on("click", "li", function(e){
		
		let liObj = $(this);
		
		let path = encodeURIComponent(liObj.data("path") + "/" + liObj.data("uuid") + "_"
						+liObj.data("filename"));
		
		//download
		self.location = "/download?fileName=" + path;
		
	});
	
	

	
	</script>
	<script src="/resources/resttest/insertReply.js"></script>
	<script src="/resources/resttest/modify.js"></script>
	<script src="/resources/resttest/delete.js"></script>
	<script src="/resources/resttest/modalClose.js"></script>
	

</body>
</html>