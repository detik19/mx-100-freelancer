package com.mx100.freelancer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REWARDS")
public class Rewards implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -902247707602027645L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated( EnumType.STRING)
	@Column(name="grade")
	private FreelancerGrade grade;
	
	@Column(name="point")
	private Integer point;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FreelancerGrade getGrade() {
		return grade;
	}

	public void setGrade(FreelancerGrade grade) {
		this.grade = grade;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
	
	

}
