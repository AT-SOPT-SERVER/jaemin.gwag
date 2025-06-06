package org.sopt.domain.user.service;

import org.sopt.domain.user.dto.CreateUserRequest;
import org.sopt.domain.user.entity.UserEntity;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.global.error.code.ErrorCode;
import org.sopt.global.error.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	@Transactional
	public void createUser(CreateUserRequest createUserRequest) {
		UserEntity userEntity = new UserEntity(createUserRequest.name());

		userRepository.save(userEntity);
	}

	public UserEntity getUserById(Long userId) {
		UserEntity userEntity = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		return userEntity;
	}

	public UserEntity getUserByName(String name) {
		UserEntity userEntity = userRepository.findByName(name)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		return userEntity;
	}
}
