	///////////////////////////////
	// 글 등록로직
	///////////////////////////////
	$("#replyAddBtn").on("click", function(){
		let replyer = $("#newReplyer").val();
		let reply = $("#newReplyText").val();
		
		$.ajax({
			type : 'post',
			url : '/replies',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				bno:bno,
				replyer:replyer,
				reply:reply
			}),
			success:function(result){
				if(result == 'SUCCESS'){
					alert("등록 되었습니다.");
					getAllList();
					$("#newReplyWriter").val('');
					$("#newReplyText").val('');
				}
			}

		});
		
	});// 글 등록로직 종료