package com.mx100.freelancer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;


public class Authority implements GrantedAuthority {

	@Id
	@Column(name ="id")
	@GeneratedValue
	private Long id;
	
    @Enumerated( EnumType.STRING)
    @Column(name="name")
	UsersRoleName usersRoleName;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
