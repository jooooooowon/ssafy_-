package com.ssafy.howmuch.model.dto;

public class ExerciseChk {
	private int year;
	private int month;
	private int day;
	private String userId;

	public ExerciseChk() {
		// TODO Auto-generated constructor stub
	}

	public ExerciseChk(int year, int month, int day, String userId) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.userId = userId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ExerciseChk [year=" + year + ", month=" + month + ", day=" + day + ", userId=" + userId + "]";
	}

}
