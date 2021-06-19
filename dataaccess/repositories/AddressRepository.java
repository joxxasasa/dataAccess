package com.iktpreobuka.dataaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
	public AddressEntity getById(Integer adrressId);
	public void deleteById(Integer id);
//	public List<AddressEntity> findAllByCity(String city);
//	public List<AddressEntity> findAllByCountryOrderByCityAsc(String country);
	//public List<AddressEntity> findAllByName(String name);
}
