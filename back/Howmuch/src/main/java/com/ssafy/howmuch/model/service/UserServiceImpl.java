package com.ssafy.howmuch.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.howmuch.model.dao.UserDao;
import com.ssafy.howmuch.model.dto.Exercise;
import com.ssafy.howmuch.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;

	@Override
	@Transactional
	public int signup(User user) {
		return dao.signup(user);
	}

	@Override
	public int login(User user) {
		System.out.println("user : " + user);
		User temp = dao.getUserById(user.getUserId());
		System.out.println("temp : " + temp);
		if (temp != null && temp.getPassword().equals(user.getPassword()))
			return 1;
		else
			return 0;
	}

	@Override
	public int deleteUser(String userId) {
		return dao.deleteUser(userId);
	}

	@Override
	public int updateUser(User user) {
		return dao.updateUser(user);
	}

	@Override
	public int getRankBySquat(String key, String userId) {
		// TODO Auto-generated method stub
		return dao.getRankBySquat(key, userId);
	}

	@Override
	public int getRankByBench(String key, String userId) {
		// TODO Auto-generated method stub
		return dao.getRankByBench(key, userId);
	}

	@Override
	public int getRankByDead(String key, String userId) {
		// TODO Auto-generated method stub
		return dao.getRankByDead(key, userId);
	}

	@Override
	public List<User> getRankListBySquat(String key, String userId) {
		// TODO Auto-generated method stub
		return dao.getRankListBySquat(key, userId);
	}

	@Override
	public List<User> getRankListByBench(String key, String userId) {
		// TODO Auto-generated method stub
		return dao.getRankListByBench(key, userId);
	}

	@Override
	public List<User> getRankListByDead(String key, String userId) {
		// TODO Auto-generated method stub
		return dao.getRankListByDead(key, userId);
	}

	@Override
	public int userIdChk(String userId) {
		// TODO Auto-generated method stub
		return dao.userIdChk(userId);
	}

	@Override
	public User getById(String userId) {
		// TODO Auto-generated method stub
		return dao.getById(userId);
	}

	@Override
	public int userNickNameChk(String nickName) {
		// TODO Auto-generated method stub
		return dao.userNickNameChk(nickName);
	}

	@Override
	public void changeMaxRecord(Exercise exercise) {
		User before = dao.getById(exercise.getUserId());
		System.out.println("before : " + before.toString());
		before.setBenchRecord(Math.max(exercise.getBenchWeight(), before.getBenchRecord()));
		before.setSquatRecord(Math.max(exercise.getSquatWeight(), before.getSquatRecord()));
		before.setDeadRecord(Math.max(exercise.getDeadWeight(), before.getDeadRecord()));
		System.out.println("after : " + before.toString());
		dao.updateMaxRecord(before);

	}

}
