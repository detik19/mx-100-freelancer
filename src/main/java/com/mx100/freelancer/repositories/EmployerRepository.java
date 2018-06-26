package com.mx100.freelancer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domain.Employer;
import com.mx100.freelancer.domain.Users;

public interface EmployerRepository extends CrudRepository<Employer, Long>{

	public Employer findOneByUsers(Users users);

}
