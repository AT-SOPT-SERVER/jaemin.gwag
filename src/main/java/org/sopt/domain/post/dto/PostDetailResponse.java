package org.sopt.domain.post.dto;

import org.sopt.domain.post.entity.PostEntity;


public record PostDetailResponse(
	String username,
	String title,
	String content,
	String category
) {

	public static PostDetailResponse of(PostEntity postEntity) {
		return new PostDetailResponse(postEntity.getTitle(), postEntity.getContent(), postEntity.getCategory().getName(), postEntity.getUser().getName());
	}
}
