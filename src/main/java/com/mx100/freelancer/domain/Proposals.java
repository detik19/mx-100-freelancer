package com.mx100.freelancer.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public class Proposals implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6917075978836308121L;
	private long id;
	private Instant proposalTime;
	private BigDecimal paymentAmount;
	
	
}
