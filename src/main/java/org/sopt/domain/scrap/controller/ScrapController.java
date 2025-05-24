package org.sopt.domain.scrap.controller;

import org.sopt.domain.scrap.sevice.ScrapService;
import org.sopt.global.error.code.SuccessCode;
import org.sopt.global.error.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scraps")
public class ScrapController {
	private final ScrapService scrapService;

	@PostMapping("/comments/{comment-id}")
	public ResponseEntity<SuccessResponse<?>> scrapComment(@PathVariable(name = "comment-id") Long commentId,
		@RequestHeader Long userId){
		scrapService.createCommentScrap(commentId,userId);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_CREATE));
	}

	@PostMapping("/posts/{post-id}")
	public ResponseEntity<SuccessResponse<?>> scrapPost(@PathVariable(name = "post-id") Long postId,
		@RequestHeader Long userId){
		scrapService.createPostScrap(postId,userId);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_CREATE));
	}
}
