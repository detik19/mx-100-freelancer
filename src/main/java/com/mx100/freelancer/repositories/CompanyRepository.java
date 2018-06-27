package com.mx100.freelancer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mx100.freelancer.domains.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

}
