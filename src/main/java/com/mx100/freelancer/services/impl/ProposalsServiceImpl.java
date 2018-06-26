package com.mx100.freelancer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx100.freelancer.domains.Proposals;
import com.mx100.freelancer.repositories.ProposalsRepository;
import com.mx100.freelancer.services.ProposalsService;

@Service
public class ProposalsServiceImpl implements ProposalsService{

	@Autowired
	private ProposalsRepository proposalRepository;
	
	@Override
	public Proposals createProposal() {
		// TODO Auto-generated method stub
		//select my proposals by jobs if exist reject TODO
		//save proposals TODO
		//reduce points TODO
		return null;
	}

}
