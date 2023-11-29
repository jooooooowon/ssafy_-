package com.ssafy.howmuch.model.service;

import java.util.List;

import com.ssafy.howmuch.model.dto.Exercise;
import com.ssafy.howmuch.model.dto.ExerciseChk;

public interface ExerciseService {
	int addExercise(Exercise exercise);

	List<Exercise> listById(String id);

	int deleteExercise(int exerciseId);
	
	String chkExercise(ExerciseChk excerciseChk);
	
	int editExercise(Exercise exercise);
}
