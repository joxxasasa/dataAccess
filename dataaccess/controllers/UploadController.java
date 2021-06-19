package com.iktpreobuka.dataaccess.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iktpreobuka.dataaccess.services.FileHandler;

@Controller
@RequestMapping("/")
public class UploadController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private FileHandler fileHandler;

	@GetMapping
	public String index() {
		return "upload";
	}
	
	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
	
	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, 
									RedirectAttributes redirectAttributes) throws IOException{
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		String result = null;
		
		result = fileHandler.singleFileUpload(file, redirectAttributes);
		
		return result;
	}
	
	
}
