package com.iktpreobuka.dataaccess.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity					
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false)
	private String street;
	@Version
	private Integer version;
	@Column(name = "users")
	private Integer numOfUsers;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserEntity> users;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "city")
	private CityEntity city;
	
	public AddressEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AddressEntity(Integer id, String street, Integer version, Integer numOfUsers, List<UserEntity> users,
			CityEntity city) {
		super();
		this.id = id;
		this.street = street;
		this.version = version;
		this.numOfUsers = numOfUsers;
		this.users = users;
		this.city = city;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getNumOfUsers() {
		return numOfUsers;
	}
	public void setNumOfUsers(Integer numOfUsers) {
		this.numOfUsers = numOfUsers;
	}
	public List<UserEntity> getUsers() {
		return users;
	}
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	public CityEntity getCity() {
		return city;
	}
	public void setCity(CityEntity city) {
		this.city = city;
	}
}
