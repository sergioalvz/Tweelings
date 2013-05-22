package com.salvarezsuar.tweelings.model;

public class Trend implements Comparable<Trend> {
	private String date;
	private Float value;

	public Trend() {}

	@Override
	public int compareTo(Trend o) {
		return this.date.compareToIgnoreCase(o.getDate());
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

}
