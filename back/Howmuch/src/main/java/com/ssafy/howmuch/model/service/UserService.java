package com.ssafy.howmuch.model.service;

import java.util.List;

import com.ssafy.howmuch.model.dto.Exercise;
import com.ssafy.howmuch.model.dto.User;

public interface UserService {

	public int userIdChk(String userId);

	public int userNickNameChk(String nickName);
	
	public User getById(String userId);
	
	public int signup(User user);

	public int login(User user);

	public int deleteUser(String userId);

	public int updateUser(User user);

	public int getRankBySquat(String key, String userId);
	
	public int getRankByBench(String key, String userId);
	
	public int getRankByDead(String key, String userId);
	
	public List<User> getRankListBySquat(String key, String userId);
	
	public List<User> getRankListByBench(String key, String userId);
	
	public List<User> getRankListByDead(String key, String userId);
	
	public void changeMaxRecord(Exercise exercise);

}
