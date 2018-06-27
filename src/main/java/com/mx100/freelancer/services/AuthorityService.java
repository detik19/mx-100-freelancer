package com.mx100.freelancer.services;

import java.util.List;

import com.mx100.freelancer.domains.Authority;
import com.mx100.freelancer.domains.enums.UsersRoleName;



public interface AuthorityService {
	  List<Authority> findById(Long id);

	  List<Authority> findByname(UsersRoleName name);
}
