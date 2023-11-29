package com.ssafy.howmuch.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

import com.ssafy.howmuch.model.dto.User;
import com.ssafy.howmuch.model.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;

	@Autowired
	private JavaMailSender emailSender;

	@PostMapping("")
	public ResponseEntity<?> signup(@RequestBody User user) {
		System.out.println(user.toString());
		int result = service.signup(user);
		if (result == 0)
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getById(@PathVariable String userId) {
		User result = service.getById(userId);
		if (result == null) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(result, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/idchk")
	public ResponseEntity<?> idChk(@RequestParam String userId) {
		if (service.userIdChk(userId) > 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	}

	@GetMapping("/namechk")
	public ResponseEntity<?> nickNameChk(@RequestParam String nickName) {
		System.out.println("nickName : " + nickName);
		if (service.userNickNameChk(nickName) > 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println("controller user : " + user);
		int result = service.login(user);
		if (result == 0)
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@GetMapping("/emailchk")
	public ResponseEntity<?> emailChk(@RequestParam String email) {
		System.out.println("email : " + email);
		// 랜덤 문자열 만들기

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		System.out.println(generatedString);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("제목");
		message.setText("내용 : " + generatedString);
		emailSender.send(message);
		// 랜덤 문자열 만들기 끝
		return new ResponseEntity<String>(generatedString, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
		int result = service.deleteUser(userId);
		if (result == 0)
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		System.out.println("update : " + user.toString());
		int result = service.updateUser(user);
		if (result == 0)
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// 유저의 개인 랭킹 보기
	// 전체 집단에서 종목별 순위를 볼 것인가? 나이별로 볼 것인가? 체급 별로 볼 것인가? 를 정해야 함
	// 그래서 종목별로 CONTROLLER 메소드를 나누고, key에서 age 인지, weight인지 전체인지 구분해서 출력하는 방식으로
	// 해볼게...
	// key: 그룹화할 기준: 없을 경우에는 그냥 전체 순위 띄우기
	// 생각해보니까 key를 pathvariable로 넘겨도 될 것 같음.
	@GetMapping("rank/squat/{userId}")
	public ResponseEntity<?> getRankBySquat(@RequestParam String key, @PathVariable String userId) {
		System.out.println("userId: " + userId);
		int result = service.getRankBySquat(key, userId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("rank/bench/{userId}")
	public ResponseEntity<?> getRankByBench(@RequestParam String key, @PathVariable String userId) {
		int result = service.getRankByBench(key, userId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("rank/dead/{userId}")
	public ResponseEntity<?> getRankByDead(@RequestParam String key, @PathVariable String userId) {
		int result = service.getRankByDead(key, userId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("rank/squat")
	public ResponseEntity<?> getRankListBySquat(@RequestParam("key") String key, @RequestParam String userId) {
		List<User> result = service.getRankListBySquat(key, userId);
		System.out.println("userId: " + userId + " key: " + key);
		System.out.println(result.toString());
		if (!result.isEmpty())
			return new ResponseEntity<List<User>>(result, HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}

	@GetMapping("rank/bench")
	public ResponseEntity<?> getRankListByBench(@RequestParam String key, @RequestParam String userId) {
		List<User> result = service.getRankListByBench(key, userId);
		if (!result.isEmpty())
			return new ResponseEntity<List<User>>(result, HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}

	@GetMapping("rank/dead")
	public ResponseEntity<?> getRankListByDead(@RequestParam String key, @RequestParam String userId) {
		List<User> result = service.getRankListByDead(key, userId);
		if (!result.isEmpty())
			return new ResponseEntity<List<User>>(result, HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
}
