package com.mx100.freelancer.repositories;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domains.Employer;
import com.mx100.freelancer.domains.Jobs;
import com.mx100.freelancer.domains.enums.JobsStatus;

public interface JobsRepository extends CrudRepository<Jobs, Long> {
	
	public List<Jobs> findAllByJobsStatusAndEmployer(JobsStatus jobStatus, Employer employer);
	public List<Jobs> findAllByJobsStatus(JobsStatus jobStatus);

}
