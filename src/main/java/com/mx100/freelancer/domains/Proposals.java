package com.mx100.freelancer.domains;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mx100.freelancer.domains.enums.ProposalStatus;

@Entity
@Table(name ="PROPOSALS")
public class Proposals implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6917075978836308121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="proposal_time")
	private Instant proposalTime;
	
	@Column(name="payment_amount",precision=10, scale=2)
	private BigDecimal paymentAmount;
	
	@Enumerated( EnumType.STRING)
	@Column(name="current_status")   
	private ProposalStatus currentStatus;
    
	@ManyToOne
	private Freelancer freelancer;
	
	@ManyToOne
	private Jobs jobs;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getProposalTime() {
		return proposalTime;
	}
	public void setProposalTime(Instant proposalTime) {
		this.proposalTime = proposalTime;
	}
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Freelancer getFreelancer() {
		return freelancer;
	}
	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}
	public Jobs getJobs() {
		return jobs;
	}
	public void setJobs(Jobs jobs) {
		this.jobs = jobs;
	}
	public ProposalStatus getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(ProposalStatus currentStatus) {
		this.currentStatus = currentStatus;
	}


}
