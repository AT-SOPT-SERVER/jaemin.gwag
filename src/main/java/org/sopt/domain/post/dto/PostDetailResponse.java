package org.sopt.domain.post.dto;

import java.time.LocalDateTime;

import org.sopt.domain.post.entity.PostEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

public record PostDetailResponse(
	String username,
	String title,
	String content,
	String category,
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
	LocalDateTime createdAt
) {

	public static PostDetailResponse of(PostEntity postEntity) {
		return new PostDetailResponse(postEntity.getUser().getName(), postEntity.getTitle(), postEntity.getContent(), postEntity.getCategory().getName(),postEntity.getCreatedAt());
	}
}
