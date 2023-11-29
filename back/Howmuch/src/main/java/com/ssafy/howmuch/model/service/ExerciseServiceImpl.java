package com.ssafy.howmuch.model.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.howmuch.model.dao.ExerciseDao;
import com.ssafy.howmuch.model.dto.Exercise;
import com.ssafy.howmuch.model.dto.ExerciseChk;

@Service
public class ExerciseServiceImpl implements ExerciseService{

	@Autowired
	ExerciseDao dao;
	
	@Override
	public int addExercise(Exercise exercise) {
		return dao.addExercise(exercise);
	}

	@Override
	public List<Exercise> listById(String id) {
		List<Exercise> list = dao.listById(id);
		if(list == null || list.size() == 0) {
			return null;
		}
		return list;
	}

	@Override
	public int deleteExercise(int exerciseId) {
		return dao.deleteExercise(exerciseId);
	}

	@Override
	public String chkExercise(ExerciseChk exerciseChk) {
		// TODO Auto-generated method stub
		return dao.chkExercise(exerciseChk);
	}

	@Override
	public int editExercise(Exercise exercise) {
		// TODO Auto-generated method stub
		return dao.editExercise(exercise);
	}

}
