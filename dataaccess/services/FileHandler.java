package com.iktpreobuka.dataaccess.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iktpreobuka.dataaccess.entities.UserEntity;

public interface FileHandler {
	
	public String singleFileUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException;
	public void singleFileRead();

}
