package org.sopt.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

	@GetMapping("/auth")
	public ResponseEntity<?> login(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		return ResponseEntity.ok(authHeader);
	}
}
