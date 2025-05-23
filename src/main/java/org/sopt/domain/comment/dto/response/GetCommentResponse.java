package org.sopt.domain.comment.dto.response;

import org.sopt.domain.comment.entity.CommentEntity;

public record GetCommentResponse(
	Long id,
	String content,
	String username
) {
	public static GetCommentResponse of(CommentEntity commentEntity) {
		return new GetCommentResponse(commentEntity.getId(), commentEntity.getContent(), commentEntity.getUserEntity().getName());
	}
}
