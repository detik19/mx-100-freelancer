package com.mx100.freelancer.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domain.Users;


@Transactional
public interface UsersRepository  extends CrudRepository<Users, Long>  {
	Users findUsersByUserName(String userName);
}
