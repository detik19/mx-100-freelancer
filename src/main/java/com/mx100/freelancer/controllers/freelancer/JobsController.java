//package com.mx100.freelancer.controllers.freelancer;
//
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mx100.freelancer.domain.Jobs;
//import com.mx100.freelancer.domain.Users;
//import com.mx100.freelancer.services.JobsService;
//import com.mx100.freelancer.utils.HeaderUtil;
//
//
//
//@RestController
//@RequestMapping("/v1/fr")
//public class JobsController {
//	private final Logger log = LoggerFactory.getLogger(JobsController.class);
//    private static final String ENTITY_NAME = "jobs";
//
//	@Autowired
//	private JobsService jobsService;
//	
//	@PutMapping("/jobs")
//	public ResponseEntity<Jobs> updateJob(@RequestBody Jobs jobs) throws URISyntaxException {
//		log.debug("REST request to update Job : {}", jobs);
//		return null;
//	}
//	/**
//	 * 
//	 * @param jobs
//	 * @return
//	 * @throws URISyntaxException
//	 */
//	@PostMapping("/jobs")
//	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
//	public ResponseEntity<Jobs> createJob(@RequestBody Jobs jobs) throws URISyntaxException {
//        log.debug("REST request to save Job : {}", jobs);
//        Jobs result=jobsService.createJobs(jobs);
//	    return ResponseEntity.ok()
//	            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobs.getId().toString()))
//	            .body(result);
//	}
//	
//
//	@GetMapping("/jobs")
//	@PreAuthorize("hasRole('ROLE_EMPLOYER','ROLE_FREELANCER')")
//	public List<Jobs> getAllJobs(@RequestParam("status") String statusSubmittedJobs) {
//		log.debug("REST request to get all Jobs");
//		List<Jobs> jobsList = new ArrayList<Jobs>();
//		jobsList=jobsService.getAllJobs();
//		return jobsList;
//	}
//	
//	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
//	public List<Jobs> getAllPublishedJobs(){
//		return null;
//		
//	}
//
//}
