package com.mx100.freelancer.services.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mx100.freelancer.domains.Freelancer;
import com.mx100.freelancer.domains.Rewards;
import com.mx100.freelancer.domains.Users;
import com.mx100.freelancer.domains.enums.FreelancerGrade;
import com.mx100.freelancer.exceptions.BusinessException;
import com.mx100.freelancer.repositories.FreelancerRepository;
import com.mx100.freelancer.repositories.RewardsRepository;
import com.mx100.freelancer.services.FreelancerService;

@Service
public class FreelancerServiceImpl implements FreelancerService {
    private final Logger log = LoggerFactory.getLogger(FreelancerServiceImpl.class);

	@Autowired
	FreelancerRepository freelancerRepository;
	
	@Autowired
	RewardsRepository rewardsRepository;
	
	@Override
	@Transactional
	public Freelancer createFreelancerProfile(Freelancer freelancer) {
		Users users = getPrincipal();
		Freelancer f=freelancerRepository.findOneByUsers(users);
		if(f!=null){
			throw new BusinessException("you was have Freelancer profile before");
		}
		Rewards r=new Rewards();
		r.setGrade(FreelancerGrade.GRADE_B);
		r.setPoint(20);
		rewardsRepository.save(r);
		
		freelancer.setRegistrationDate(new Date().toInstant());
		freelancer.setUsers(users);
		freelancer.setRewards(r);
		
		return freelancerRepository.save(freelancer);
	}
	
	private  Users getPrincipal(){
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	}
	
}
