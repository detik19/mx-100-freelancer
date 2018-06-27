package com.mx100.freelancer.controllers;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx100.freelancer.domains.Jobs;
import com.mx100.freelancer.domains.Proposals;
import com.mx100.freelancer.services.ProposalsService;
import com.mx100.freelancer.utils.HeaderUtil;

@RestController
@RequestMapping("/v1/")
public class ProposalsController {
	private final Logger log = LoggerFactory.getLogger(JobsController.class);
	private static final String ENTITY_NAME = "PROPOSALS";
	
	@Autowired
	private ProposalsService proposalService;
	
	/**
	 * Create Proposal
	 * @param proposal
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping("/fr/proposals")
	public ResponseEntity<Proposals> createProposal(@RequestBody Proposals proposal) throws URISyntaxException {
		log.debug("REST request to save Proposal : {}", proposal);
		Proposals result =proposalService.createProposal(proposal, proposal.getJobs().getId());
		//
		//    Proposal result = proposalRepository.save(proposal);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, proposal.getId().toString()))
				.body(result);
	}
	/**
	 * get All Proposal submitted by a Freelancer
	 * @return
	 * @throws URISyntaxException
	 */
	@GetMapping("/fr/proposals")
	public List<Proposals> getAllMyProposals() throws URISyntaxException {
		return proposalService.findAllUsersProposal();

	}
	@GetMapping("/em/proposals")
	public List<Proposals> getAllProposalsForJobs(@RequestParam("jobsId") String jobsId) throws URISyntaxException {
		return proposalService.findAllProposalForAJobs(Long.getLong(jobsId));
		

	}

}
