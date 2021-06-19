package com.iktpreobuka.dataaccess.controllers;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.DomainEvents;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.iktpreobuka.dataaccess.entities.CountryEntity;
import com.iktpreobuka.dataaccess.repositories.CityRepository;
import com.iktpreobuka.dataaccess.repositories.CountryRepository;

public class CountryControllers {

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@PostMapping
	public CountryEntity addNewCountry(@RequestParam String name) {
		CountryEntity country = new CountryEntity();
		country.setName(name);
		return countryRepository.save(country);
		}
	
	@GetMapping
	public Iterable<CountryEntity> getAll() {
		return countryRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public CountryEntity getById(@PathVariable Integer id) {
		return countryRepository.findById(id).get();
	}
	
	@PutMapping("/{id}")
	public CountryEntity changeCountry(@PathVariable Integer id, @RequestBody CountryEntity changedCountry) {
		CountryEntity country = countryRepository.findById(id).get();
		if(changedCountry.getName() != null)
			country.setName(changedCountry.getName());
		return countryRepository.save(country);
	}
	
	@DeleteMapping("/{id}")
	public void delCountryById(@PathVariable Integer id) {
		countryRepository.deleteById(id);
	}
}
