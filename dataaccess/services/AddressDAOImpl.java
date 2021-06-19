package com.iktpreobuka.dataaccess.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.iktpreobuka.dataaccess.entities.AddressEntity;

@Service
public class AddressDAOImpl implements AddressDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AddressEntity> findAddressesByName(@PathVariable String name) {
		//TODO napravi SQL upit
		// SELECT * FROM AddressEntity a LEFT JOIN UserEntity u ON a.id ==u.address WHERE u.name =: name
		String sql = "SELECT a FROM AddressEntity a LEFT JOIN FETCH a.users u WHERE u.name = :name";
		//TODO Treba da pozovemo HQL
		Query query = em.createQuery(sql);
		query.setParameter("name", name);
		
		List<AddressEntity> retVals = query.getResultList();
		//TODO obraditi vracene podatke i return 
		
		return retVals;
	}

}
