package com.mx100.freelancer.services;

import java.util.List;

import com.mx100.freelancer.domains.Proposals;
import com.mx100.freelancer.exceptions.BusinessException;

public interface ProposalsService {
	public Proposals createProposal(Proposals proposal, Long jobsId) throws BusinessException;
	public List<Proposals> findAllUsersProposal();
	public List<Proposals> findAllProposalForAJobs(Long jobsId);


}
