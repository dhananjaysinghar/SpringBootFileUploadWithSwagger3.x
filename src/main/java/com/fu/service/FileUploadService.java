package com.fu.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.SneakyThrows;

@Service
public class FileUploadService {

	@Value("${app.file.upload-location}")
	private String uploadPath;

	@SneakyThrows
	public String save(MultipartFile file) {
		Path path = Paths.get(uploadPath).toAbsolutePath().normalize();
		String extension = file.getOriginalFilename().split("\\.")[1];

		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		String fileName = file.getOriginalFilename().split("\\.")[0]
				.concat("_" + time)
				.concat(".")
				.concat(extension);

		path = path.resolve(fileName);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);


		return ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName).toUriString();
	}
}
