package org.sopt.domain.comment.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommentRequest(
	@NotNull(message = "내용은 필수 입력 값입니다")
	@Size(min = 0, max = 300)
	String content
) {
}
