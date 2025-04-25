package org.sopt.api.dto;

import org.sopt.api.domain.PostEntity;

import lombok.Builder;

@Builder
public record PostResponse(
	Long postId,
	String title
) {
	public static PostResponse of(Long postId, String title) {
		return new PostResponse(post.getId(), post.getTitle());
	}

	public static PostResponse from(PostEntity postEntity) {
		return new PostResponse(postEntity.getId(), postEntity.getTitle());
	}
}
