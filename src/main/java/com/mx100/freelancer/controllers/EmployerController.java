package com.mx100.freelancer.controllers;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx100.freelancer.domains.Employer;
import com.mx100.freelancer.services.EmployerService;
import com.mx100.freelancer.utils.HeaderUtil;

@RestController
@RequestMapping("/v1/")
public class EmployerController {
	private final Logger log = LoggerFactory.getLogger(EmployerController.class);

	private static final String ENTITY_NAME = "employer";

	@Autowired
	EmployerService employerService;
	/**
	 * Create employer profile
	 * 
	 * @param employer
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/em/employer")
	public ResponseEntity<Employer> createEmployer(@Valid @RequestBody Employer employer) throws URISyntaxException {
		Employer result= employerService.createEmployerProfile(employer);
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employer.getId().toString()))
				.body(result);


	}

}
