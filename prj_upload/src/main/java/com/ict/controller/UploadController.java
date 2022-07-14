package com.ict.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ict.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	private String getFolder() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
		
	} // getFolder End
	
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	} //  checkImageType END
	
	
	@GetMapping("/uploadForm")
	public void uploadFrom() {
		log.info("upload Form");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		
		String uploadFolder = "C:\\upload_data\\temp";
		
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
		
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				
				multipartFile.transferTo(saveFile);
				
			}catch(Exception e) {
				log.error(e.getMessage());
			}
		}
		
	} // uploadFormPost END
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
		log.info("upload ajax");
		
	} // uploadAjax END
	
	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		
		log.info("ajax post update!");
		
		String uploadFolder = "C:\\upload_data\\temp";
		
		File uploadPath = new File(uploadFolder, getFolder());
		
		log.info(uploadPath);
		
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdir();
			
		
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("------------------------");
			log.info("Upload file name : " + multipartFile.getOriginalFilename());
			log.info("Upload file size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//UUID 발급 합니다.
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
					
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			log.info("last file name : " + uploadFileName);
			
			//File saveFile = new File(uploadFolder, uploadFileName);
			
			
			
			try {
				
				File saveFile = new File(uploadPath, uploadFileName);
				
				multipartFile.transferTo(saveFile);
				
				// 썸네일 생성로직 start
				if(checkImageType(saveFile)) {
					FileOutputStream thumbnail =
							new FileOutputStream(
									new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(
							multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				
			} catch(Exception e) {
				log.error(e.getMessage());
			}
		}
		
		
	} // uploadAjaxPost END
	
	

	@PostMapping(value="/uploadAjaxFormAction", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadFormPost(MultipartFile[] uploadFile){
		
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "C:\\upload_data\\temp";
		
		String uploadFolderPath = getFolder();
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			log.info("only file name : " + uploadFileName);
		
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
		
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					
					thumbnail.close();
				}
				list.add(attachDTO);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}// end for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}// uploadFormPost
	
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		
		log.info("fileName 디버깅 : " + fileName);
		
		File file = new File("C:\\upload_data\\temp\\" + fileName);
		
		log.info("새로 생성한 file 디버깅 : " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),
											header,
											HttpStatus.OK);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return result;
	} // ResponseEntity END
	
	
	@GetMapping(value="/download",
					produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName) {
		
		log.info("downloadFile 실행 ! fileName 디버깅 : " + fileName);
		
		Resource resource = new FileSystemResource(
								"C:\\upload_data\\temp\\" + fileName);
		
		log.info("resource 를 한 번 보자 : " + resource);
		
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		// 다운로드 시 저장되는 이름을 Content-Disposition으로 저장
		// 첨부파일이니까 attachment
		try {
			headers.add("Content-Disposition",
					"attachment; filename=" +
						new String(resourceName.getBytes("UTF-8"),
												"ISO-8859-1"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 다운이 완료되면 resource와 headers, 200을 Entity에 담아서 보내주세요		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		
	} // downloadFile END
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		
		log.info("deleteFile로 삭제할 파일 이름 : " + fileName);
		// 깡통 파일 생성
		File file = null;
		
		try {
			file = new File("C:\\upload_data\\temp\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			if(type.equals("image")) {
				
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				
				log.info("큰파일네임 : "+largeFileName);
				
				file = new File(largeFileName);
				
				file.delete();
			}
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}// deleteFile END
	
	

	
	
}
