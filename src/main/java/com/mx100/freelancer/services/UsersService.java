package com.mx100.freelancer.services;

import java.util.List;

import com.mx100.freelancer.domains.UserRequest;
import com.mx100.freelancer.domains.Users;



public interface UsersService {
	  void resetCredentials();

	  Users findById(Long id);

	  Users findByUsername(String username);

	  List<Users> findAll();

	  Users save(UserRequest userRequest);
}
