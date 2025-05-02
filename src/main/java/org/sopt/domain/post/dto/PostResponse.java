package org.sopt.domain.post.dto;

import java.time.LocalDateTime;

import org.sopt.domain.post.entity.PostEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

public record PostResponse(
	String title,
	String username,
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
	LocalDateTime createdAt
) {
	public static PostResponse from(PostEntity postEntity) {
		return new PostResponse(postEntity.getTitle(), postEntity.getUser().getName(), postEntity.getCreatedAt());
	}
}
