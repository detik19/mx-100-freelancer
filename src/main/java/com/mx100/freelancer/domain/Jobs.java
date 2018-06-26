package com.mx100.freelancer.domain;

import java.io.Serializable;
import java.time.Instant;

public class Jobs implements Serializable{
	private Long id;
	private String title;
	private String description;
	private Instant deadline;
	private Instant updatedOn;
	private JobsStatus jobsStatus;
	private Users postedBy;
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
	public Instant getDeadline() {
		return deadline;
	}
	public void setDeadline(Instant deadline) {
		this.deadline = deadline;
	}
	public Instant getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}
	public JobsStatus getJobsStatus() {
		return jobsStatus;
	}
	public void setJobsStatus(JobsStatus jobsStatus) {
		this.jobsStatus = jobsStatus;
	}
	public Users getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(Users postedBy) {
		this.postedBy = postedBy;
	}
	

}
