package com.salvarezsuar.tweelings.model;

import com.salvarezsuar.tweelings.model.types.TwitterJobType;

public class TwitterJob {
	private String token;
	private String observations;	

	private String file;
	private String description;
	private Long id;
	private TwitterJobType type;

	public TwitterJob() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TwitterJobType getType() {
		return type;
	}

	public void setType(TwitterJobType type) {
		this.type = type;
	}
	
	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}
}
