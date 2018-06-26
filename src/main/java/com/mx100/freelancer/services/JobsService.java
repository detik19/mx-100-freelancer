package com.mx100.freelancer.services;

import java.util.List;

import com.mx100.freelancer.domain.Jobs;
import com.mx100.freelancer.domain.JobsStatus;

public interface JobsService {
	public List<Jobs> getAllJobs();
	public List<Jobs> getAllJobsByStatus(JobsStatus jobsStatus);
	public List<Jobs> getAllMyJobsByStatus(JobsStatus jobsStatus);

	public Jobs createJobs(Jobs jobs);
	public Jobs publishJobs();

}
