package com.ssafy.howmuch.model.dao;

import java.util.List;

import com.ssafy.howmuch.model.dto.User;

public interface UserDao {
	public User getUserById(String userId);
	public int signup (User user);
	public int deleteUser (String userId);
	public int updateUser (User user);
	
	
	// 아이디 중복체크를 위한 idList
	public int userIdChk(String userId);
	// 닉네임 중복체크를 위한 idList
	public int userNickNameChk(String nickName);
	// 마이페이지에 보여줄 유저 정보들
	public User getById(String userId);
	
	
	// 이거할 때 파라미터로 string하고 int값 받기 힘들거야
	// 그러니까 class를 하나 만들어서 해야돼
	// 그래서 저번에 searchCondition 클래스가 따로 있었나봐
	public int getRankBySquat(String key, String userId);
	
	public int getRankByBench(String key, String userId);
	
	public int getRankByDead(String key, String userId);
	
	public List<User> getRankListBySquat(String key, String userId);
	
	public List<User> getRankListByBench(String key, String userId);
	
	public List<User> getRankListByDead(String key, String userId);
	
	public int updateMaxRecord(User user);
	
}

