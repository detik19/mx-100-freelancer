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

import com.mx100.freelancer.domains.Freelancer;
import com.mx100.freelancer.services.FreelancerService;
import com.mx100.freelancer.utils.HeaderUtil;

@RestController
@RequestMapping("/v1/")
public class FreelancerController {
    private final Logger log = LoggerFactory.getLogger(FreelancerController.class);

    private static final String ENTITY_NAME = "freelancer";
    

    @Autowired
    FreelancerService freelancerService;
    /**
     * Create Freelancer Controller
     * @param freelancer
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/fr/freelancers")
    public ResponseEntity<Freelancer> createFreelancer(@Valid @RequestBody Freelancer freelancer) throws URISyntaxException {
		Freelancer result=freelancerService.createFreelancerProfile(freelancer);
		
      return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, freelancer.getId().toString()))
	            .body(result);
    	
    	
    }
    
    
}
