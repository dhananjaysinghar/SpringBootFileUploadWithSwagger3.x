package com.fu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fu.service.FileUploadService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadService service;
	
	
	@Operation(description = "Upload Document")
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		return service.save(file);
	}

}
