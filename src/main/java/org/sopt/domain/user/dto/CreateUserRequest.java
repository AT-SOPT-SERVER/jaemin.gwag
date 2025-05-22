package org.sopt.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
	@NotNull(message = "이름은 필수 값입니다")
	@Size(min = 1, max = 10, message = "이름은 최대 10글자 이하여야합니다")
	String name
) {
}
