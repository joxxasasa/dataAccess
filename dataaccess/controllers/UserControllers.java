package com.iktpreobuka.dataaccess.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iktpreobuka.dataaccess.entities.AddressEntity;
import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.repositories.AddressRepository;
import com.iktpreobuka.dataaccess.repositories.UserRepository;
import com.iktpreobuka.dataaccess.services.FileHandler;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllers {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private FileHandler fileHandler;
	
	@RequestMapping(method = RequestMethod.POST)
	public UserEntity createUser(@RequestParam String name, @RequestParam String email, @RequestParam LocalDate dateOfBirth,
									@RequestParam String telephoneNumber, @RequestParam String jmbg, @RequestParam String idCard) {
		UserEntity user = new UserEntity();
		user.setName(name);
		user.setEmail(email);
		user.setDateOfBirth(dateOfBirth);
		user.setTelephoneNumber(telephoneNumber);
		user.setJmbg(jmbg);
		user.setIdCard(idCard);
		UserEntity retUser = userRepository.save(user);
		//userRepository.save(user);
		return retUser;
}
	@RequestMapping(method = RequestMethod.GET)
	public List<UserEntity> getAllUsers() {
	return (List<UserEntity>) userRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/address")
	public UserEntity addAddress(@PathVariable Integer id, @RequestParam Integer addressId) {
		UserEntity user = userRepository.findById(id).get();
		AddressEntity address = addressRepository.findById(addressId).get();
		user.setAddress(address);
		return userRepository.save(user);
	}
	
//	• 1.3 omogućiti pronalaženje korisnika po email adresi
//	• putanja /by-email
	@RequestMapping(method = RequestMethod.GET, path = "/by-email")
	public UserEntity findUserByEmail(@RequestParam String email) {
		return userRepository.findByEmail(email);
	}
	
//	• 1.4 omogućiti pronalaženje korisnika po imenu
//	• vraćanje korisnika sortiranih po rastućoj vrednosti emaila
//	• putanja /by-name
	@GetMapping("/by-name")
	public List<UserEntity> findUserByName(@RequestParam String name) {
		return userRepository.findByNameOrderByEmailAsc(name);
	}
	
//	• 2.2 omogućiti pronalaženje korisnika po datumu rođenja sortiranih 
//	u rastućem redosledu imena
//	• putanja /by-dob
	
	@GetMapping("/by-dob")
	public List<UserEntity> findByDateOfBirthOrderByNameAsc(@RequestParam LocalDate dateOfBirth) {
		return userRepository.findByDateOfBirthOrderByNameAsc(dateOfBirth);
	}
	
//	• 2.3* omogućiti pronalaženje različitih imena korisnika po prvom 
//	slovu imena
//	• putanja /by-name-first-letter
	
	@GetMapping("/by-name-first-letter")
	public List<UserEntity> findByNameStartsWith(@RequestParam String firstLetter) {
		return userRepository.findByNameStartsWith(firstLetter);
	}
	
//	• 2.1 dodati REST entpoint u UserController koji omogućava 
//	uklanjanje adrese iz entiteta korisnika
	
	@GetMapping("/del-address/{id}")
	public UserEntity removeAddress(@PathVariable Integer id) {
		UserEntity user = userRepository.findById(id).get();
		user.setAddress(null);
		return userRepository.save(user);
	}
	
//	• 2.3* dodati REST entpoint u UserController koji omogućava 
//	prosleđivanje parametara za kreiranje korisnika i adrese
//	• kreira korisnika
//	• proverava postojanje adrese
//	• ukoliko adresa postoji u bazi podataka dodaje je korisniku
//	• ukoliko adresa ne postoji, kreira adresu i dodaje je korisniku
	
//	@PostMapping("/with-address")
//	public UserEntity createUserWithAddress(@RequestParam String name, @RequestParam String email, @RequestParam LocalDate dateOfBirth,
//									@RequestParam String telephoneNumber, @RequestParam String jmbg, @RequestParam String idCard,
//									@RequestParam Integer addressId, @RequestParam String street) {
//		UserEntity user = new UserEntity();
//		user.setName(name);
//		user.setEmail(email);
//		user.setDateOfBirth(dateOfBirth);
//		user.setTelephoneNumber(telephoneNumber);
//		user.setJmbg(jmbg);
//		user.setIdCard(idCard);
//		AddressEntity address;
//		if(!addressRepository.existsById(addressId)) {
//			address = new AddressEntity();
//			address.setStreet(street);
//			addressRepository.save(addressNew);
//			user.setAddress(addressNew);
//		}else {
//			address= addressRepository.findById(addressId).get();
//		}
//		AddressEntity addres = addressRepository.findById(addressId).orElseGet(()->{
//			AddressEntity addressNew = new AddressEntity();
//			addressNew.setStreet(street);
//			addressRepository.save(addressNew);
//			user.setAddress(addressNew);
//			return addressNew;
//		});
//		if(address != null) {
//			user.setAddress(address);
//		} else {
//			
//		}	
//		UserEntity retUser = userRepository.save(user);
//		return retUser;
//}
//	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/readAndSaveUsers")
	public String uploadReadAndSaveUsersFronCsv(@RequestParam("file") MultipartFile file, 
									RedirectAttributes redirectAttributes) {
		try {
			fileHandler.singleFileUpload(file, redirectAttributes);
			fileHandler.singleFileRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "File is uploaded";
	}
	
}