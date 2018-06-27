package com.mx100.freelancer.services.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mx100.freelancer.domains.Company;
import com.mx100.freelancer.domains.Employer;
import com.mx100.freelancer.domains.Users;
import com.mx100.freelancer.exceptions.BusinessException;
import com.mx100.freelancer.repositories.CompanyRepository;
import com.mx100.freelancer.repositories.EmployerRepository;
import com.mx100.freelancer.services.EmployerService;

@Service
public class EmployerServiceImpl implements EmployerService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployerRepository employerRepository;
	
	@Override
	@Transactional
	public Employer createEmployerProfile(Employer employer) {
		Users users = getPrincipal();
		Employer e=employerRepository.findOneByUsers(users);
		if(e!=null){
			throw new BusinessException("you was have Employer profile before");
		}
		
		Company c=employer.getCompany();
		c=companyRepository.save(c);
		employer.setCompany(c);
		employer.setRegistrationDate(new Date().toInstant());
		return employerRepository.save(e);
	}

	private  Users getPrincipal(){
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	}
}
