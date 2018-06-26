package com.mx100.freelancer.services.impl;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mx100.freelancer.domains.Employer;
import com.mx100.freelancer.domains.Jobs;
import com.mx100.freelancer.domains.Users;
import com.mx100.freelancer.domains.enums.JobsStatus;
import com.mx100.freelancer.repositories.EmployerRepository;
import com.mx100.freelancer.repositories.JobsRepository;
import com.mx100.freelancer.services.JobsService;

@Service
public class JobsServiceImpl implements JobsService{
	private final Logger log = LoggerFactory.getLogger(JobsServiceImpl.class);

	@Autowired
	private JobsRepository jobsRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Override
	public List<Jobs> getAllJobs() {
		List<Jobs> jobsList=(List<Jobs>) jobsRepository.findAll();
		return jobsList;
	}

	@Override
	public List<Jobs> getAllJobsByStatus(JobsStatus jobsStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jobs createJobs(Jobs jobs) {
		log.debug("create jobs service");
		Users users=getPrincipal();
        Employer employer=employerRepository.findOneByUsers(users);
		jobs.setEmployer(employer);
		
		
        return jobsRepository.save(jobs);
	}

	@Override
	public Jobs publishJobs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jobs> getAllMyJobsByStatus(JobsStatus jobsStatus) {
		Users users=getPrincipal();
        Employer employer=employerRepository.findOneByUsers(users);
        List<Jobs> jobsList=jobsRepository.findAllByJobsStatusAndEmployer(jobsStatus, employer);
		return jobsList;
	}
	
	private  Users getPrincipal(){
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	}

}
