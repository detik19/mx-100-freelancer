package com.mx100.freelancer.repositories;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domains.Employer;
import com.mx100.freelancer.domains.Jobs;
import com.mx100.freelancer.domains.enums.JobsStatus;

public interface JobsRepository extends CrudRepository<Jobs, Long> {
	
	public List<Jobs> findAllByJobStatusAndEmployer(JobsStatus jobsStatus, Employer employer);
	public List<Jobs> findAllByJobStatus(JobsStatus jobsStatus);
	public Jobs findOneByIdAndEmployerAndJobStatus(Long id, Employer employer, JobsStatus jobStatus);
	public Jobs findOneByIdAndJobStatus(Long id, JobsStatus jobStatus);

}
