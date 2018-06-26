package com.mx100.freelancer.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mx100.freelancer.domains.Users;
import com.mx100.freelancer.repositories.UsersRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users users = usersRepository.findUsersByUserName(userName);
		if (users == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userName));
		} else {
			return users;
		}
	}

	public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        if (authenticationManager != null) {
            log.debug("Re-authenticating user '"+ username + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
        	log.debug("No authentication manager set. can't change Password!");

            return;
        }

        log.debug("Changing password for user '"+ username + "'");

        Users users = (Users) loadUserByUsername(username);

        users.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(users);

    }
}
