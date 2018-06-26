package com.mx100.freelancer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domains.Authority;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	Authority findByUsersRoleName(String UsersRoleName);

}
