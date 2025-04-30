package org.sopt.domain.post.dto;

import org.sopt.domain.post.entity.PostEntity;

public record PostResponse(
	String title,
	String username
) {
	public static PostResponse from(PostEntity postEntity) {
		return new PostResponse(postEntity.getTitle(), postEntity.getUser().getName());
	}
}
