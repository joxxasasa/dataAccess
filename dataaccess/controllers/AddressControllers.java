package com.iktpreobuka.dataaccess.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.AddressEntity;
import com.iktpreobuka.dataaccess.entities.CityEntity;
import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.repositories.AddressRepository;
import com.iktpreobuka.dataaccess.repositories.CityRepository;
import com.iktpreobuka.dataaccess.repositories.UserRepository;
import com.iktpreobuka.dataaccess.services.AddressDAO;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressControllers {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressDAO addressService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CityRepository cityRepository;

	@RequestMapping(method = RequestMethod.POST)
	public AddressEntity addNewAddress(@RequestParam String street) {
		AddressEntity address = new AddressEntity();
		address.setStreet(street);
		return addressRepository.save(address);
		
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<AddressEntity> getAllAddresses() {
		return addressRepository.findAll();
	}
	
//	• 1.2 u potpunosti omogućiti rad sa adresama
//	• vraćanje adrese po ID
//	• ažuriranje adrese
//	• brisanje adrese
	
	@GetMapping("/find-by-id")//ZBOG CEGA MI JE TRAZENO DA TIP BUDE "OPTIONAL"?
	public AddressEntity getById(@RequestParam Integer addressId) {
		return addressRepository.findById(addressId).get();
	}
	
	@PutMapping("/{id}")
	public AddressEntity changeAddress(@PathVariable Integer id, @RequestBody AddressEntity changedAddress) {
		AddressEntity address = addressRepository.findById(id).get();
		if(changedAddress.getStreet() != null)
		address.setStreet(changedAddress.getStreet());
		return addressRepository.save(address);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		addressRepository.deleteById(id); //poslati objekat koji je obrisan
	}
	

////	• 1.3 omogućiti pronalaženje adrese po gradu
////	• putanja /by-city
//	
//	@GetMapping("/by-city/{city}")
//	public List<AddressEntity> findAllByCity(@PathVariable String city) {
//		return addressRepository.findAllByCity(city);
//	}
//	
////	• 1.4 omogućiti pronalaženje adrese po državi
////	• vraćanje adresa sortiranih po rastućoj vrednosti države
////	• putanja /by-country
//	
//	@GetMapping("/by-country/{country}")
//	public List<AddressEntity> findAllByCountryOrderByCityAsc(@PathVariable String country) {
//		return addressRepository.findAllByCountryOrderByCityAsc(country);
//	}
	
//	• 2.2 u AddressController dodati REST entpoint-e za dodavanje i 
//	brisanje korisnika u adresama
	
	@PutMapping("/{id}/addUser")
	public AddressEntity addUser(@PathVariable Integer id, @RequestParam Integer userId) {
		AddressEntity address= addressRepository.findById(id).get();
		UserEntity user = userRepository.findById(userId).get();
		address.getUsers().add(user);
		address.setNumOfUsers(address.getUsers().size());
		return address;
	}
	
	@GetMapping("/{id}/removeUser")
	public AddressEntity removeUser(@PathVariable Integer id, @RequestParam Integer userId) {
		AddressEntity address = addressRepository.findById(id).get();
		UserEntity user = userRepository.findById(userId).get();
		address.getUsers().remove(user);
		address.setNumOfUsers(address.getUsers().size());
		return address;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/city")
	public AddressEntity addCity(@PathVariable Integer id, @RequestParam Integer cityId) {
		AddressEntity address = addressRepository.findById(id).get();
		CityEntity city = cityRepository.findById(cityId).get();
		address.setCity(city);
		return addressRepository.save(address);
	}
	
//	• Kreirati REST endpoint
//	• Koji vraća listu adresa na osnovu prosleđenog imena korisnika
//	• putanja /api/v1/addresses/user/{name}

	
	@GetMapping("/user/{name}")
	public List<AddressEntity> findAddressesByName(@PathVariable String name) {
		return addressService.findAddressesByName(name);
	}
}
