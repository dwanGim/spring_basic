	// 댓글 수정 로직
	
	$("#replyModBtn").on("click", function(){
		
		let rno = $(".modal-title").html();
		let reply = $("#replyText").val();
		
		$.ajax({
			type : 'put',
			url : '/replies/' + rno,
			header : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PATCH"
			},
			contentType : "application/json",
			data : JSON.stringify({reply:reply}),
			dataType : 'text',
			
			success : function(result) {
				console.log("result : " +result);
				
				if(result == 'SUCCESS') {
					
					alert("수정되었습니다");
					$("#modDiv").hide("slow");
					getAllList();
					
				}	
			}
		});
	});