package com.mx100.freelancer.controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx100.freelancer.domains.Jobs;
import com.mx100.freelancer.domains.enums.JobsStatus;
import com.mx100.freelancer.services.JobsService;
import com.mx100.freelancer.utils.HeaderUtil;


@RestController
@RequestMapping("/v1/")
public class JobsController {
	private final Logger log = LoggerFactory.getLogger(JobsController.class);
    private static final String ENTITY_NAME = "jobs";

	@Autowired
	private JobsService jobsService;
	
	@PutMapping("/em/jobs")
	public ResponseEntity<Jobs> updateJob(@RequestBody Jobs jobs) throws URISyntaxException {
		log.debug("REST request to update Job : {}", jobs);
		return null;
	}
	
	
	/**
	 * 
	 * @param jobs
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/em/jobs")
	public ResponseEntity<Jobs> createJob(@RequestBody Jobs jobs) throws URISyntaxException {
        log.debug("REST request to save Job : {}", jobs);
        Jobs result=jobsService.createJobs(jobs);
	    return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobs.getId().toString()))
	            .body(result);
	}
	

	@GetMapping("/em/jobs")
	public List<Jobs> getAllMyJobs(@RequestParam("status") JobsStatus statusSubmittedJobs) {
		log.debug("REST request to get all Jobs created by employee");
		List<Jobs> jobsList = new ArrayList<Jobs>();
		//jobsList=jobsService.getAllJobs();
		jobsList=jobsService.getAllMyJobsByStatus(statusSubmittedJobs);
		return jobsList;
	}
	
	
	@GetMapping("/fr/jobs")
	public List<Jobs> getAllJobsPublished() {
		log.debug("REST request to get all Jobs publsihed");
		List<Jobs> jobsList = new ArrayList<Jobs>();
		jobsList=jobsService.getAllJobsByStatus(JobsStatus.PUBLISHED);
		return jobsList;
	}
}

