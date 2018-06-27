package com.mx100.freelancer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domains.Freelancer;
import com.mx100.freelancer.domains.Rewards;
import com.mx100.freelancer.domains.Users;

public interface RewardsRepository extends CrudRepository<Rewards, Long>{
//	Rewards findOneByFreelancer(Freelancer freelancer);
}
