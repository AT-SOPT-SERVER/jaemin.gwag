package org.sopt.domain.post.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostListResponse(
	@JsonProperty(value = "postList")
	List<PostResponse> postResponseList
) {
	public static PostListResponse of(List<PostResponse> postResponseList) {
		return new PostListResponse(postResponseList);
	}
}
