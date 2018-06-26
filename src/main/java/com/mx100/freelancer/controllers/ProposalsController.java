package com.mx100.freelancer.controllers;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx100.freelancer.domains.Jobs;
import com.mx100.freelancer.domains.Proposals;
import com.mx100.freelancer.utils.HeaderUtil;

@RestController
@RequestMapping("/v1/")
public class ProposalsController {
	private final Logger log = LoggerFactory.getLogger(JobsController.class);
	private static final String ENTITY_NAME = "PROPOSALS";

	@PostMapping("/fr/proposals")
	public ResponseEntity<Proposals> createProposal(@RequestBody Proposals proposal) throws URISyntaxException {
		log.debug("REST request to save Proposal : {}", proposal);

		//
		//    Proposal result = proposalRepository.save(proposal);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, proposal.getId().toString()))
				.body(proposal);
	}
	
	public List<Jobs> getAllMyProposals() throws URISyntaxException {
		return null;

	}
	
	public List<Jobs> getAllProposalsToMe() throws URISyntaxException {
		return null;

	}

}
