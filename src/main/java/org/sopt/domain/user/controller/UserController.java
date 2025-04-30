package org.sopt.domain.user.controller;

import org.sopt.domain.user.dto.CreateUserRequest;
import org.sopt.domain.user.service.UserService;
import org.sopt.global.error.code.SuccessCode;
import org.sopt.global.error.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<SuccessResponse<?>> signUP(@RequestBody CreateUserRequest createUserRequest){
		userService.createUser(createUserRequest);

		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_CREATE));
	}

}
