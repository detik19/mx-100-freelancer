package com.mx100.freelancer.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mx100.freelancer.domains.UserRequest;
import com.mx100.freelancer.domains.Users;
import com.mx100.freelancer.repositories.UsersRepository;
import com.mx100.freelancer.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void resetCredentials() {
		// TODO Auto-generated method stub

	}

	@Override
	public Users findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users save(UserRequest userRequest) {
		Users users=new Users();
		users.setUserName(userRequest.getUsername());
		users.setPassword(passwordEncoder.encode(userRequest.getUsername()));
		users.setFirstName(userRequest.getFirstname());
		users.setLastName(userRequest.getLastname());
//	    List<Authority> auth = authService.findByname("ROLE_USER");
//	    user.setAuthorities(auth);
		usersRepository.save(users);
		return users;
	}

}
