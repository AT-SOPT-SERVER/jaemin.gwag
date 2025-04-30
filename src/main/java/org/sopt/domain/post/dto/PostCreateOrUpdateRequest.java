package org.sopt.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCreateOrUpdateRequest(
	@JsonProperty(value = "title")
	@NotBlank(message = "제목은 필수 입력값입니다")
	@Size(min = 1, max = 30, message = "제목은 최소 한 글자, 최대 30글자이어야합니다")
	String title,

	@NotBlank(message = "내용은 필수 입력값입니다")
	@Size(min = 1, max = 1000, message = "내용은 최소 한 글자, 최대 1000글자 이하여야합니다")
	String content,

	@NotBlank
	String category
) {
}
