package org.sopt.api.dto;

import org.sopt.api.domain.PostEntity;


public record PostResponse(
	Long postId,
	String title
) {
	public static PostResponse of(PostEntity postEntity) {
		return new PostResponse(postEntity.getId(), postEntity.getTitle());
	}

	public static PostResponse from(PostEntity postEntity) {
		return new PostResponse(postEntity.getId(), postEntity.getTitle());
	}
}
