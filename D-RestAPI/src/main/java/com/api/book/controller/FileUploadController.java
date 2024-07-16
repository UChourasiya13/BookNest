package com.api.book.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.book.helper.FileUploadHelp;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadHelp fileUploadHelper;
	
	@PostMapping("upload-file")
	public ResponseEntity<String> fileupload(@RequestParam("file")MultipartFile file)
	{
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getSize()); // in byte 
//		System.out.println(file.getContentType());
//		System.out.println(file.getName());
		
		
		if(file.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Must have non-empty file");
		
		if(!file.getContentType().equals("image/jpeg"))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("jpeg file type is allowed only");
		
		// file upload code..
		
		boolean res = fileUploadHelper.uploadFile(file);
		
		if(res==true)
		return ResponseEntity.ok("File upload successfully");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong; try again !!");
		
	}
}
