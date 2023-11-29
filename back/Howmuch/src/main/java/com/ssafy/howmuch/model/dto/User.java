package com.ssafy.howmuch.model.dto;

import java.util.Date;

public class User {
	private String userId;
	private String password;
	private String email;
	private String nickname;
	private Date birthday;
	private int weight;
	private int height;
	private int benchRecord;
	private int squatRecord;
	private int deadRecord;
	
	public User() {
		
	}
	
	
	public User(String userId, String password, String email, String nickname, Date birthday, int weight, int height) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.birthday = birthday;
		this.weight = weight;
		this.height = height;
	}
	
	
	
	

	public User(String userId, String password, String email, String nickname, Date birthday, int weight, int height,
			int benchRecord, int squatRecord, int deadRecord) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.birthday = birthday;
		this.weight = weight;
		this.height = height;
		this.benchRecord = benchRecord;
		this.squatRecord = squatRecord;
		this.deadRecord = deadRecord;
	}

	

	public int getBenchRecord() {
		return benchRecord;
	}


	public void setBenchRecord(int benchRecord) {
		this.benchRecord = benchRecord;
	}


	public int getSquatRecord() {
		return squatRecord;
	}


	public void setSquatRecord(int squatRecord) {
		this.squatRecord = squatRecord;
	}


	public int getDeadRecord() {
		return deadRecord;
	}


	public void setDeadRecord(int deadRecord) {
		this.deadRecord = deadRecord;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", email=" + email + ", nickname=" + nickname
				+ ", birthday=" + birthday + ", weight=" + weight + ", height=" + height + ", benchRecord="
				+ benchRecord + ", squatRecord=" + squatRecord + ", deadRecord=" + deadRecord + "]";
	}


	
	
}
