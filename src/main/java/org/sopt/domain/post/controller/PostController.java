package org.sopt.domain.post.controller;

import org.sopt.domain.post.dto.PostCreateOrUpdateRequest;
import org.sopt.domain.post.dto.PostListResponse;
import org.sopt.domain.post.dto.PostDetailResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService ;

	public PostController(PostService postService){
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<SuccessResponse<?>> createPost(@Valid @RequestBody final PostCreateOrUpdateRequest postRequest,
		@RequestHeader("userId") Long userId){
		postService.createPost(userId, postRequest);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_CREATE));
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<?>> getAllPosts(){
		PostListResponse postListResponse = postService.getPostAll();
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_FETCH, postListResponse));
	}

	@GetMapping("/{post-id}")
	public ResponseEntity<SuccessResponse<?>> getPostById(@PathVariable(name = "post-id") Long postId){
		PostDetailResponse postResponse = postService.getPost(postId);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_FETCH, postResponse));
	}

	@DeleteMapping("/{post-id}")
	public ResponseEntity<SuccessResponse<?>> deletePostById(@PathVariable(name = "post-id") Long postId,
		@RequestHeader Long userId){
		postService.deletePostById(userId, postId);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_DELETE));
	}

	@GetMapping("/search/title")
	public ResponseEntity<SuccessResponse<?>> searchPostsByTitle(@RequestParam(name = "title") String title) {
		PostListResponse postListResponse = postService.getByTitle(title);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_FETCH, postListResponse));

	}

	@GetMapping("/search/category")
	public ResponseEntity<SuccessResponse<?>> searchPostsByCategory(@RequestParam(name = "category") String category,
	@RequestHeader Long userId) {
		PostListResponse postListResponse = postService.getByCategory(category);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_FETCH, postListResponse));
	}

	@GetMapping("/search/username")
	public ResponseEntity<SuccessResponse<?>> searchPostsByUser(@RequestParam(name = "username") String userName) {
		PostListResponse postListResponse = postService.getByUserName(userName);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_FETCH, postListResponse));
	}

	@PatchMapping("/{post-id}")
	public ResponseEntity<SuccessResponse<?>> updatePostTitle(@PathVariable(name = "post-id") Long postId,
		@Valid @RequestBody PostCreateOrUpdateRequest postRequest,
		@RequestHeader Long userId){
		postService.updatePost(userId, postId, postRequest);
		return ResponseEntity.ok(SuccessResponse.of(SuccessCode.SUCCESS_UPDATE));
	}
}
