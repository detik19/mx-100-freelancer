package com.mx100.freelancer.domains;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="FREELANCER")
public class Freelancer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2614683854544673887L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @OneToOne
    @JoinColumn(unique = true)	
    private Users users;
	
    @Column(name = "registration_date")
    private Instant registrationDate;
    @Column(name = "location")
	private String location;
    @Column(name = "overview")
	private String overview;
    
    @OneToOne
    @JoinColumn(unique=true)
    private Rewards rewards;
    
    @OneToMany(mappedBy = "freelancer")
    @JsonIgnore
    private Set<Certification> certificationLists = new HashSet<>();
    
//    @ManyToMany
//    @JoinTable(name = "freelancer_technical_skills",
//               joinColumns = @JoinColumn(name="freelancers_id", referencedColumnName="id"),
//               inverseJoinColumns = @JoinColumn(name="technical_skills_id", referencedColumnName="id"))
//    private Set<TechnicalSkill> technicalSkills = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Instant getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Instant registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}


	public Rewards getRewards() {
		return rewards;
	}

	public void setRewards(Rewards rewards) {
		this.rewards = rewards;
	}

//	public Set<TechnicalSkill> getTechnicalSkills() {
//		return technicalSkills;
//	}
//
//	public void setTechnicalSkills(Set<TechnicalSkill> technicalSkills) {
//		this.technicalSkills = technicalSkills;
//	}
    
    

}
