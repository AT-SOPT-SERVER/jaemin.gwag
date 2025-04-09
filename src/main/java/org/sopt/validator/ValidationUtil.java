package org.sopt.validator;

import org.sopt.repository.PostRepository;

public class ValidationUtil {

	private final PostRepository postRepository;

	public ValidationUtil(PostRepository postRepository){
		this.postRepository = postRepository;
	}

	public void validatePost(int postId){
		if(postRepository.findPostById(postId) == null){
			throw new IllegalArgumentException("존재하지 않는 게시물입니다");
		}
	}

	public void validateTitle(String title){
		if((title.isEmpty()) || (title.length() > 30)){
			throw new IllegalArgumentException("제목은 1자 이상, 30자 이하여야 합니다");
		}
	}
}
