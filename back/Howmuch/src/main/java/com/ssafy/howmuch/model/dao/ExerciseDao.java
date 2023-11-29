package com.ssafy.howmuch.model.dao;

import java.util.List;

import com.ssafy.howmuch.model.dto.Exercise;
import com.ssafy.howmuch.model.dto.ExerciseChk;

public interface ExerciseDao {
	int addExercise(Exercise exercise);
	
	String chkExercise(ExerciseChk exerciseChk);
	
	List<Exercise> listById(String id);
	
	int deleteExercise(int exerciseId);
	
	int editExercise(Exercise exercise);
}
