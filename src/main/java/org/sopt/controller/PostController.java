package org.sopt.controller;

import java.util.List;

import org.sopt.Util.IDGenerator;
import org.sopt.Util.ValidationUtil;
import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.service.PostService;

public class PostController {

	private final PostService postService = new PostService();

	public Post createPost(String title){
		Post post = new Post(IDGenerator.generateId(),title);

		Post createdPost = postService.createPost(post);
		return createdPost;
	}

	public List<Post> getAllPosts(){
		return postService.findAll();
	}

	public Post getPostById(int postId){
		return postService.getPostById(postId);
	}

	public boolean deletePostById(int postId){
		return postService.deletePostById(postId);
	}

	public List<Post> searchPostsByKeyword(String keyword) {
		List<Post> postList = postService.getByKeyword(keyword);
		return null;
	}

	/* 게시글 수정 기능 */
	public boolean updatePostTitle(final int postId, final String title){
		boolean isUpdated = postService.updatePost(postId, title);
		return isUpdated;
	}
}
