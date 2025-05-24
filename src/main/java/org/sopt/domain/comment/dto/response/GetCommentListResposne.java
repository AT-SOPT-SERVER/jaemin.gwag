package org.sopt.domain.comment.dto.response;

import java.util.List;

public record GetCommentListResposne(
	List<GetCommentResponse> getCommentResponseList
) {
	public static GetCommentListResposne of(List<GetCommentResponse> getCommentResponseList) {
		return new GetCommentListResposne(getCommentResponseList);
	}
}
