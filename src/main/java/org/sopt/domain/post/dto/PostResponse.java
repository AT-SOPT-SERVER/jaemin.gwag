package org.sopt.domain.post.dto;

import org.sopt.domain.post.entity.PostEntity;


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
