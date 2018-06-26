package com.mx100.freelancer.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYER")
public class Employer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2763215209625206914L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @OneToOne
    @JoinColumn(unique = true)	
    private Users users;
    
	@Column(name ="registration_date")
	private Integer registrationDate;
	
	@Column(name = "location")
	private String location;
	@ManyToOne
	private Company company;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Integer registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
