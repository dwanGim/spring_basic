<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/resources/uploadAjax.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 글쓸때, 제목 글쓴이, 본문 을 채우고 submit을 눌러야 합니다.
	vo에 적힌 명칭을 감안해서 제목, 글쓴이 본문을 쓸 수 있도록 폼태그를 완성시켜주세요. -->
	<form action="" method="post">
		<input type="text" name="title" required/>
		<input type="text" name="writer" required/>
		<textarea name="content" required></textarea>
		<input type="submit">
	</form>
		<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<div class="uploadResult">
		<ul>
			<!-- 업로드된 파일들이 여기 나열됨. -->
		</ul>
	</div>
	
	
	<button id="uploadBtn">Upload</button>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script>
		let csrfHeaderName = "${_csrf.headerName}";
		let csrfTokenValue= "${_csrf.token}";
	
		$(document).ready(function(){
			
			// 정규표현식 : 예).com 끝나는 문장 등의 조건이 복잡한 문장을 컴퓨터에게 이해시키기 위한 구문
			let regex = new RegExp("(.*)\.(exe|sh|zip|alz)$");
								// 파일이름 .  exe|sh|zip|alz 인 경우를 체크함
			let maxSize =5242880; // 5Mb
			
			function checkExtension(fileName, fileSize){
				// 파일크기 초과시 종료시킴
				if(fileSize >= maxSize){
					alert("파일 사이즈 초과");
					return false;// return이 있어서 아래쪽 구문은 실행 안됨
				}
				// regex에 표현해둔 정규식과 일치하는지 여부를 체크, 일치하면 true, 아니면 false
				if(regex.test(fileName)){
					alert("해당 확장자를 가진 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}
			
			let cloneObj = $(".uploadDiv").clone();
			
			$('#uploadBtn').on("click", function(e){
			
				let formData = new FormData();
				
				let inputFile = $("input[name='uploadFile']");
				
				let files = inputFile[0].files;
				console.log(files);
				
				// 파일 데이터를 폼에 집어넣기
				for(let i = 0; i < files.length; i++){
					if(!checkExtension(files[i].name, files[i].size)){
						return false;// 조건에 맞지않은 파일 포함시 onclick 이벤트 함수자체를 종료시켜버림
					}
					
					formData.append("uploadFile", files[i]);
				}
				console.log("--------------파일 적재 후 formData 태그 -------------");
				console.log(formData);
				
				$.ajax({
					url: '/board/uploadAjaxFormAction', 
					beforeSend : function(xhr) {
						 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						 },
					processData : false,
					contentType: false,
					data : formData,
					dataType:'json',
					type : 'POST',
					success : function(result){
						console.log(result);
						
						showUploadedFile(result);
						
						$(".uploadDiv").html(cloneObj.html());
					}
				}); // ajax
				
				
			});// uploadBtn onclick
			
			
			let uploadResult = $(".uploadResult ul");
			
			function showUploadedFile(uploadResultArr){
				let str = "";
				
				//      json데이터    하나씩      i = 인덱스 번호입니다 obj = json본체
				$(uploadResultArr).each(function(i, obj){
					
					console.log(obj);
					console.log(obj.image);
					
					if(!obj.image){
						// 이미지가 아니라면
						let fileCallPath = encodeURIComponent(
											obj.uploadPath + "/"
											+ obj.uuid + "_" + obj.fileName);
						
						str += `<li data-path='\${obj.uploadPath}' data-uuid='\${obj.uuid}'
									data-filename='\${obj.fileName}' data-type='\${obj.image}'>
									<a href='/board/download?fileName=\${fileCallPath}'>
										<img src='/resources/fileThumbnail.png'>\${obj.fileName}
									</a>
									<span class='btn btn-min' data-file='\${fileCallPath}' data-type='file'> X </span>
								</li>`;
						
					}else{
						// 이미지 파일이라면
						//str += `<li>\${obj.fileName}</li>`;
						//이미지 썸네일이 출력되도록 처리
						
						let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" +
															obj.uuid + "_" + obj.fileName);
						
						// 썸네일을 다운 받지 않도록 fileCallPath를 하나 더 만들어주세요
						let fileCallPath2 = encodeURIComponent(
								obj.uploadPath + "/"
								+ obj.uuid + "_" + obj.fileName);
						
						
						str += `<li data-path='\${obj.uploadPath}' data-uuid='\${obj.uuid}'
									data-filename='\${obj.fileName}' data-type='\${obj.image}'>
									<a href='/board/download?fileName=\${fileCallPath2}'>
										<img src='/board/display?fileName=\${fileCallPath}'>\${obj.fileName}
									</a>
									<span data-file='\${fileCallPath}' data-type='image'> X </span>
								</li>`;
					} //  if/else END
					
				});
				console.log(str);

				uploadResult.append(str);
			}// showUploadedFile
			
			$(".uploadResult").on("click", "span", function(e){
				
				
				// .data 는 데어터 뒤에 있는 것 data-type의 type, date-file의 file을 가져옵니다.
				let targetFile = $(this).data("file");
				let type = $(this).data("type");
				
				// 클릭한 span 태그와 엮여있는 li를 지정
				let targetLi = $(this).closest("li");
				
				$.ajax({
					url : '/board/deleteFile',
					beforeSend : function(xhr) {
						 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						 },
				//fileName이란 이름으로 targetFile, type이란 이름으로 type 보내기
					data : {fileName: targetFile, type:type},
					dataType : 'text',
					type : 'POST',
					success: function(result) {
						alert(result);
						// 클릭한 li요소를 화면에서 삭제하는 remove(화면에서 삭제됨)
						targetLi.remove();
					}
					
				}); //  ajax
				
			}); // .uploadResult onclick span END
			
			
		});	// document ready END
	
	</script>
</body>
</html>