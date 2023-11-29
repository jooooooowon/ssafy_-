package com.ssafy.howmuch.model.dto;

import java.util.Date;

public class Exercise {
	private int exerciseId;
	private String userId;
	private Date startTime;
	private Date endTime;
	private int squatWeight;
	private int squatCount;
	private int squatSet;
	private int deadWeight;
	private int deadCount;
	private int deadSet;
	private int benchWeight;
	private int benchCount;
	private int benchSet;

	public Exercise() {
		// TODO Auto-generated constructor stub
	}
	
	public Exercise(int exerciseId, String userId, Date startTime, Date endTime, int squatWeight, int squatCount,
			int squatSet, int deadWeight, int deadCount, int deadSet, int benchWeight, int benchCount, int benchSet) {
		super();
		this.exerciseId = exerciseId;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.squatWeight = squatWeight;
		this.squatCount = squatCount;
		this.squatSet = squatSet;
		this.deadWeight = deadWeight;
		this.deadCount = deadCount;
		this.deadSet = deadSet;
		this.benchWeight = benchWeight;
		this.benchCount = benchCount;
		this.benchSet = benchSet;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getSquatWeight() {
		return squatWeight;
	}

	public void setSquatWeight(int squatWeight) {
		this.squatWeight = squatWeight;
	}

	public int getSquatCount() {
		return squatCount;
	}

	public void setSquatCount(int squatCount) {
		this.squatCount = squatCount;
	}

	public int getSquatSet() {
		return squatSet;
	}

	public void setSquatSet(int squatSet) {
		this.squatSet = squatSet;
	}

	public int getDeadWeight() {
		return deadWeight;
	}

	public void setDeadWeight(int deadWeight) {
		this.deadWeight = deadWeight;
	}

	public int getDeadCount() {
		return deadCount;
	}

	public void setDeadCount(int deadCount) {
		this.deadCount = deadCount;
	}

	public int getDeadSet() {
		return deadSet;
	}

	public void setDeadSet(int deadSet) {
		this.deadSet = deadSet;
	}

	public int getBenchWeight() {
		return benchWeight;
	}

	public void setBenchWeight(int benchWeight) {
		this.benchWeight = benchWeight;
	}

	public int getBenchCount() {
		return benchCount;
	}

	public void setBenchCount(int benchCount) {
		this.benchCount = benchCount;
	}

	public int getBenchSet() {
		return benchSet;
	}

	public void setBenchSet(int benchSet) {
		this.benchSet = benchSet;
	}

	@Override
	public String toString() {
		return "Exercise [exerciseId=" + exerciseId + ", userId=" + userId + ", startTime=" + startTime + ", endTime="
				+ endTime + ", squatWeight=" + squatWeight + ", squatCount=" + squatCount + ", squatSet=" + squatSet
				+ ", deadWeight=" + deadWeight + ", deadCount=" + deadCount + ", deadSet=" + deadSet + ", benchWeight="
				+ benchWeight + ", benchCount=" + benchCount + ", benchSet=" + benchSet + "]";
	}

}
