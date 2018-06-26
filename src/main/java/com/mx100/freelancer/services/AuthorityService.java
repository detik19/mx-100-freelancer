package com.mx100.freelancer.services;

import java.util.List;

import com.mx100.freelancer.domains.Authority;



public interface AuthorityService {
	  List<Authority> findById(Long id);

	  List<Authority> findByname(String name);
}
