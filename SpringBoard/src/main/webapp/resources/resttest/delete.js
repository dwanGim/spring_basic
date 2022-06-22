	$("#replyDelBtn").on("click", function(){
	
		let rno = $(".modal-title").html();
		
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			header : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result) {
				console.log("result:  " + result);
				if (result == 'SUCCESS') {
					alert("삭제되었습니다");
					// 모달 닫기
					$("#modDiv").hide("slow");
					// 삭제된 이후 목록 갱신하기
					getAllList();
					
				}
			}
		});		
	}); // replyDelBtn END
	