package org.sopt.domain.post.controller;

import org.sopt.domain.post.dto.PostCreateOrUpdateRequest;
import org.sopt.domain.post.dto.PostListResponse;
import org.sopt.domain.post.dto.PostResponse;
import org.sopt.domain.post.service.PostService;
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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class PostController {

	private final PostService postService ;

	public PostController(PostService postService){
		this.postService = postService;
	}

	@PostMapping("/post/{userId}")
	public ResponseEntity<SuccessResponse<?>> createPost(@Valid @RequestBody final PostCreateOrUpdateRequest postRequest,
		@RequestHeader("userId") Long userId){
		postService.createPost(userId, postRequest);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_CREATE));
	}

	@GetMapping("/posts")
	public ResponseEntity<SuccessResponse<?>> getAllPosts(){
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_FETCH, postService.findAll()));
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<SuccessResponse<?>> getPostById(@PathVariable Long postId){
		PostResponse postResponse = postService.getPost(postId);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_FETCH, postResponse));
	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<SuccessResponse<?>> deletePostById(Long postId){
		postService.deletePostById(postId);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_DELETE));
	}

	@GetMapping("/post/search?keyword={keyword}")
	public ResponseEntity<SuccessResponse<?>> searchPostsByKeyword(String keyword) {
		PostListResponse postListResponse = postService.getByKeyword(keyword);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_FETCH, postListResponse));

	}

	@PatchMapping("/post/{postId}")
	public ResponseEntity<SuccessResponse<?>> updatePostTitle(@PathVariable Long postId,
		@Valid @RequestBody PostCreateOrUpdateRequest postRequest){
		postService.updatePost(postId, postRequest.title());
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_UPDATE));
	}
}
