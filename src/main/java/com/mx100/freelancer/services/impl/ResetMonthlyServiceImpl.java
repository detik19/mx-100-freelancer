package com.mx100.freelancer.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.mx100.freelancer.domains.Rewards;
import com.mx100.freelancer.domains.enums.FreelancerGrade;
import com.mx100.freelancer.repositories.RewardsRepository;
import com.mx100.freelancer.services.ResetMonthlyService;

public class ResetMonthlyServiceImpl implements ResetMonthlyService{

	@Autowired
	RewardsRepository rewardsRepository;
	
	@Override @Transactional
	@Scheduled(cron = "${cron.reset.points}")
	public void resetAllRewards() {
		List<Rewards> rewardsList=(List<Rewards>) rewardsRepository.findAll();
		for(Rewards r:rewardsList){
			if(r.getGrade().equals(FreelancerGrade.GRADE_A)){
				r.setPoint(40);
			}else {
				r.setPoint(20);
			}
		}
		
	}

}
