package com.lcwd.todo.controllers;

import org.springframework.http.MediaType;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {
	Logger logger=LoggerFactory.getLogger(FileController.class);

	@PostMapping("/single")
	public String uploadSingle(@RequestParam("image") MultipartFile file)
	{
		logger.info("Name : {}",file.getName());
		logger.info("ContentType {}" ,file.getContentType());
		logger.info("Original File Name {}" ,file.getOriginalFilename());
		logger.info("File Size {}" ,file.getSize());
		//file.getInputStream
//		InputStream inputStream=file.getInputStream();
//		FileOutputStream fileOutputStream=new FileOutputStream("data.png");
        return "File Test";
	}
	@PostMapping("/multiple")
	public String uploadMultiple(@RequestParam("files") MultipartFile[] files)
	{
		Arrays.stream(files).forEach(file->{
			logger.info("ContentType {}" ,file.getContentType());
			logger.info("Original File Name {}" ,file.getOriginalFilename());
		});
	
		/*
file.getInputStream
		InputStream inputStream=file.getInputStream();
		FileOutputStream fileOutputStream=new FileOutputStream("data.png");
*/
		return "File Test";
	}
	@GetMapping("/serve-image")
	public void serveImageHandler(HttpServletResponse response)
	{
		try {
			InputStream fileInputStream= new FileInputStream("images/download.jpg");
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(fileInputStream, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
 