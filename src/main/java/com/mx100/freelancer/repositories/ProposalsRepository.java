package com.mx100.freelancer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domains.Employer;
import com.mx100.freelancer.domains.Freelancer;
import com.mx100.freelancer.domains.Jobs;
import com.mx100.freelancer.domains.Proposals;

public interface ProposalsRepository extends CrudRepository<Proposals, Long> {
	Proposals findOneByFreelancerAndJobs(Freelancer freelancer, Jobs jobs);
	List<Proposals> findAllByFreelancer(Freelancer freelancer);
	List<Proposals> findAllByJobs(Jobs jobs);

}
