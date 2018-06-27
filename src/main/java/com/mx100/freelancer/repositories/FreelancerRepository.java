package com.mx100.freelancer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domains.Freelancer;
import com.mx100.freelancer.domains.Users;

public interface FreelancerRepository  extends CrudRepository<Freelancer, Long>{
	public Freelancer findOneByUsers(Users users);

}
