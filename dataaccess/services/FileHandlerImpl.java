package com.iktpreobuka.dataaccess.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iktpreobuka.dataaccess.controllers.UserControllers;
import com.iktpreobuka.dataaccess.entities.AddressEntity;
import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.repositories.AddressRepository;
import com.iktpreobuka.dataaccess.repositories.UserRepository;

@Service
public class FileHandlerImpl implements FileHandler{

	private static String UPLOAD_FOLDER = "C:\\SpringTemp\\";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserControllers userControllers;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public String singleFileUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		if(file.isEmpty()) {
		redirectAttributes.addFlashAttribute("message", "Please select file to upload");
		return "redirect:uploadStatus";
		
		}
		
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message", "File " + file.getOriginalFilename() + " succesfuly uploaded");
		
		return "redirect:/uploadStatus";
	}
//procitati fajl file reader 

//	@Override
//	public List<UserEntity> singleFileRead(String file) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	public void singleFileRead() {
		Scanner s = null;
	try {
		UserEntity user = new UserEntity();
		s = new Scanner(new File("C:\\SpringTemp\\upload.csv"));
		s.useDelimiter(",");
		while (s.hasNext()) {
			String name = s.next("C:\\SpringTemp\\upload.csv");
			user.setName(name);
			String email = s.next("C:\\SpringTemp\\upload.csv");
			user.setEmail(email);
			user.setDateOfBirth(null);
			user.setTelephoneNumber(null);
			user.setJmbg(null);
			user.setIdCard(null);
			user.setAddress(null);
			s.close();
	}
			//sacuvati korisnika u bazu
		userRepository.save(user);
	}
	catch (IOException e) {
		System.out.println(e.getMessage());
	}
	finally {
		if (s != null) {
			s.close();
			}
		}
	}
}
