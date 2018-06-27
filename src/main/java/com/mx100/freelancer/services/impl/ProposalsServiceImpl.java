package com.mx100.freelancer.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mx100.freelancer.domains.Freelancer;
import com.mx100.freelancer.domains.Jobs;
import com.mx100.freelancer.domains.Proposals;
import com.mx100.freelancer.domains.Rewards;
import com.mx100.freelancer.domains.Users;
import com.mx100.freelancer.domains.enums.JobsStatus;
import com.mx100.freelancer.exceptions.BusinessException;
import com.mx100.freelancer.repositories.FreelancerRepository;
import com.mx100.freelancer.repositories.JobsRepository;
import com.mx100.freelancer.repositories.ProposalsRepository;
import com.mx100.freelancer.repositories.RewardsRepository;
import com.mx100.freelancer.services.ProposalsService;

@Service
public class ProposalsServiceImpl implements ProposalsService{

	@Autowired
	private ProposalsRepository proposalRepository;
	
	@Autowired
	private FreelancerRepository freelancerRepository;
	
	@Autowired
	private RewardsRepository rewardsRepository;
	
	@Autowired
	private JobsRepository jobsRepository;
	
	@Override
	public Proposals createProposal(Proposals proposal, Long jobsId) throws BusinessException {
		Users user=getPrincipal();
		Freelancer freelancer=freelancerRepository.findOneByUsers(user);
		Rewards reward=freelancer.getRewards();
		//Rewards reward=rewardsRepository.findOneByFreelancer(freelancer);
		if(reward.getPoint()==0){
			throw new BusinessException("your proposals limit allowed has been reached");
		}
	
		Jobs j=jobsRepository.findOneByIdAndJobStatus(jobsId, JobsStatus.PUBLISHED);
		if(j==null){
			throw new BusinessException("your jobs id is wrong");

		}
		Proposals p=proposalRepository.findOneByFreelancerAndJobs(freelancer,j);
		if(p!=null){
			throw new BusinessException("you only allow to post on proposal such jobs");

		}
		proposal.setJobs(j);
		proposal.setFreelancer(freelancer);
		
		Proposals result=proposalRepository.save(proposal);
		
		reward.setPoint(reward.getPoint()-2);
		rewardsRepository.save(reward);

		return result;
	}
	private  Users getPrincipal(){
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	}
	@Override
	public List<Proposals> findAllUsersProposal() {
		Users user=getPrincipal();
		Freelancer freelancer=freelancerRepository.findOneByUsers(user);
		return proposalRepository.findAllByFreelancer(freelancer);
	}
	@Override
	public List<Proposals> findAllProposalForAJobs(Long jobsId) {
		Users user=getPrincipal();
		Optional<Jobs> jOpt=jobsRepository.findById(jobsId);
		if(!jOpt.isPresent()){
			throw new BusinessException("Jobs id is wrong");

		}
		String userName=jOpt.get().getEmployer().getUsers().getUserName();
		if(!userName.equals(user.getUsername())){
			throw new BusinessException("Jobs id is wrong");

		}
		List<Proposals> proposalList=proposalRepository.findAllByJobs(jOpt.get());
		//Optional<Proposals> pOpt=proposalRepository.find;

		
		
		return proposalList;
	}
}
