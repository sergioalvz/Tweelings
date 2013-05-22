package com.salvarezsuar.twitterfeelinganalyzer.model;

public class Tweet {
	private String text;
	private String date;
	private String user;
	private String lang;

	public Tweet() {}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
