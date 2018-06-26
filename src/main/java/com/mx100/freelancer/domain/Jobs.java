package com.mx100.freelancer.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JOBS")
public class Jobs implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7104957753914281412L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name = "amount", precision=10, scale=2)
	private BigDecimal budgetAmount;
    
	@Enumerated(EnumType.STRING)
    @Column(name = "job_status")
    private JobsStatus jobStatus;
    
	@ManyToOne
	private Employer employer;
	
    @ManyToMany
    @JoinTable(name = "job_skills_required",
               joinColumns = @JoinColumn(name="jobs_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="skills_requireds_id", referencedColumnName="id"))
    private Set<TechnicalSkill> skillsRequireds = new HashSet<>();
    
    @Column(name = "expected_duration")
    private String expectedDuration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(BigDecimal budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public JobsStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobsStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Set<TechnicalSkill> getSkillsRequireds() {
		return skillsRequireds;
	}

	public void setSkillsRequireds(Set<TechnicalSkill> skillsRequireds) {
		this.skillsRequireds = skillsRequireds;
	}

	public String getExpectedDuration() {
		return expectedDuration;
	}

	public void setExpectedDuration(String expectedDuration) {
		this.expectedDuration = expectedDuration;
	}

	@Override
	public String toString() {
		return "Jobs [id=" + id + ", title=" + title + ", description=" + description + ", budgetAmount=" + budgetAmount
				+ ", jobStatus=" + jobStatus + ", employer=" + employer + ", skillsRequireds=" + skillsRequireds
				+ ", expectedDuration=" + expectedDuration + "]";
	}

    


}
