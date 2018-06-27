package com.mx100.freelancer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domains.Authority;
import com.mx100.freelancer.domains.enums.UsersRoleName;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	Authority findByUsersRoleName(UsersRoleName UsersRoleName);

}
