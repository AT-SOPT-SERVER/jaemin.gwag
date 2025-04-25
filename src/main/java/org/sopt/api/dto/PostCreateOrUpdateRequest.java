package org.sopt.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCreateOrUpdateRequest(
	@JsonProperty(value = "title")
	@NotBlank
	@Size(min = 1, max = 30, message = "제목은 최소 한 글자, 최대 30글자이어야합니다")
	String title
) {
}
