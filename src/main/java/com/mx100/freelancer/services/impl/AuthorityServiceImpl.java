package com.mx100.freelancer.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx100.freelancer.domains.Authority;
import com.mx100.freelancer.domains.enums.UsersRoleName;
import com.mx100.freelancer.repositories.AuthorityRepository;
import com.mx100.freelancer.services.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public List<Authority> findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Authority> auth=authorityRepository.findById(id);
		List<Authority> auths = new ArrayList<>();
		auth.ifPresent(auths::add);
		return auths;
	}

	@Override
	public List<Authority> findByname(UsersRoleName name) {
		Authority auth = this.authorityRepository.findByUsersRoleName(name);
		List<Authority> auths = new ArrayList<>();
		auths.add(auth);
		return auths;	
	}

}
