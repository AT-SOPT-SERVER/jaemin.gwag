package org.sopt.domain.comment.controller;

import org.sopt.domain.comment.dto.request.CreateCommentRequest;
import org.sopt.domain.comment.dto.request.UpdateCommentRequest;
import org.sopt.domain.comment.dto.response.GetCommentListResposne;
import org.sopt.domain.comment.service.CommentService;
import org.sopt.global.error.code.SuccessCode;
import org.sopt.global.error.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
	private final CommentService commentService;

	@PostMapping("/{post-id}")
	public ResponseEntity<SuccessResponse<?>> postComment(@PathVariable(name = "post-id") Long postId,
		@RequestHeader Long userId,
		@RequestBody CreateCommentRequest createCommentRequest){
		commentService.createComment(userId, postId, createCommentRequest);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_CREATE));
	}

	@PatchMapping("/{post-id}")
	public ResponseEntity<SuccessResponse<?>> updateComment(@PathVariable(name = "post-id") Long postId,
		@RequestHeader Long userId,
		@RequestBody UpdateCommentRequest updateCommentRequest){
		commentService.updateComment(userId, postId, updateCommentRequest);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_CREATE));
	}


	@GetMapping("/{post-id}")
	public ResponseEntity<SuccessResponse<?>> getAllComment(@PathVariable(name = "post-id") Long postId){
		GetCommentListResposne getCommentListResposne = commentService.getAllCommentList(postId);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_FETCH, getCommentListResposne));
	}

	@DeleteMapping("/{comment-id}")
	public ResponseEntity<SuccessResponse<?>> deleteComment(@PathVariable(name = "comment-id") Long commentId,
		@RequestHeader Long userId){
		commentService.deleteComment(commentId, userId);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_DELETE));
	}

}
