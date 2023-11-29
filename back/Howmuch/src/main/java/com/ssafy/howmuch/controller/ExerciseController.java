package com.ssafy.howmuch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.howmuch.model.dto.Exercise;
import com.ssafy.howmuch.model.dto.ExerciseChk;
import com.ssafy.howmuch.model.service.ExerciseService;
import com.ssafy.howmuch.model.service.UserService;

@RestController
@RequestMapping("/exercise")
@CrossOrigin("*")
public class ExerciseController {
	@Autowired
	ExerciseService service;

	@Autowired
	UserService userService;
	
	@GetMapping("/{loginId}")
	public ResponseEntity<?> getListById(@PathVariable String loginId) {
		List<Exercise> list = service.listById(loginId);
		if (list == null) {
			return new ResponseEntity<List<Exercise>>(new ArrayList<>(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Exercise>>(list, HttpStatus.OK);
		}
	}

	@GetMapping("/chk")
	public ResponseEntity<?> getListById(@RequestParam int year, int month, int day, String userId) {
		ExerciseChk exerciseChk = new ExerciseChk(year, month, day, userId);
		String result = service.chkExercise(exerciseChk);
		System.out.println("result : " +result);
		if(result == null) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(Integer.parseInt(result), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<?> addExercise(@RequestBody Exercise exercise) {
		System.out.println("post exercise : " + exercise);
		int result = service.addExercise(exercise);
		if (result == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		} else {
			System.out.println("post success");
			userService.changeMaxRecord(exercise);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	}

	@PutMapping("")
	public ResponseEntity<?> editExercise(@RequestBody Exercise exercise) {
		System.out.println("put exercise : " + exercise);
		int result = service.editExercise(exercise);
		if (result == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		} else {
			System.out.println("success");
			userService.changeMaxRecord(exercise);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	}

	@DeleteMapping("/{exerciseId}")
	public ResponseEntity<?> deleteExercise(@PathVariable int exerciseId) {
		int result = service.deleteExercise(exerciseId);
		if (result == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	}
}
