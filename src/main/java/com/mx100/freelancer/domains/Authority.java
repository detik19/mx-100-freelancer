package com.mx100.freelancer.domains;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.mx100.freelancer.domains.enums.UsersRoleName;

@Entity
@Table(name="AUTHORITY")
public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8416836223302715484L;

	@Id
	@Column(name ="id")
	@GeneratedValue
	private Long id;
	
    @Enumerated( EnumType.STRING)
    @Column(name="name")
	UsersRoleName usersRoleName;

	@Override
	public String getAuthority() {
		return usersRoleName.name();
	}
	

}
