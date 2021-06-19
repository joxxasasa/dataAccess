package com.iktpreobuka.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.CityEntity;
import com.iktpreobuka.dataaccess.entities.CountryEntity;
import com.iktpreobuka.dataaccess.repositories.AddressRepository;
import com.iktpreobuka.dataaccess.repositories.CityRepository;
import com.iktpreobuka.dataaccess.repositories.CountryRepository;

@RestController
@RequestMapping("/api/v1/cities")
public class CityControllers {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@PostMapping
	public CityEntity addNewCity(@RequestParam String name) {
		CityEntity city = new CityEntity();
		city.setName(name);
		return cityRepository.save(city);
	}
	
	@GetMapping
	public Iterable<CityEntity> getAllCities() {
		return cityRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public CityEntity getById(@PathVariable Integer id) {
		return cityRepository.findById(id).get();
	}
	
	@PutMapping("/{id}")
	public CityEntity changeCity(@PathVariable Integer id, @RequestBody CityEntity changedCity) {
		CityEntity city = cityRepository.findById(id).get();
		if(changedCity.getName() != null)
			city.setName(changedCity.getName());
		return cityRepository.save(city);
	}
	
	@DeleteMapping("/{id}")
	public void delCityById(@PathVariable Integer id) {
		cityRepository.deleteById(id);
	}
	
	
}
