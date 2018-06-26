package com.mx100.freelancer.domains;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "CERTIFICATION")
public class Certification implements Serializable  {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = -5391399291705773467L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "certification_name")
	    private String certificationName;

	    @Column(name = "provider")
	    private String provider;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "date_earned")
	    private Instant dateEarned;

	    @Column(name = "url_link")
	    private String urlLink;

	    @ManyToOne
	    private Freelancer freelancer;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCertificationName() {
			return certificationName;
		}

		public void setCertificationName(String certificationName) {
			this.certificationName = certificationName;
		}

		public String getProvider() {
			return provider;
		}

		public void setProvider(String provider) {
			this.provider = provider;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Instant getDateEarned() {
			return dateEarned;
		}

		public void setDateEarned(Instant dateEarned) {
			this.dateEarned = dateEarned;
		}

		public String getUrlLink() {
			return urlLink;
		}

		public void setUrlLink(String urlLink) {
			this.urlLink = urlLink;
		}

		public Freelancer getFreelancer() {
			return freelancer;
		}

		public void setFreelancer(Freelancer freelancer) {
			this.freelancer = freelancer;
		}
		

	    
	    
}
