package org.sopt.service;

import java.util.List;

import org.sopt.Util.IDGenerator;
import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.Util.ValidationUtil;

public class PostService {
	private final PostRepository postRepository;
	private final ValidationUtil validationUtil;

	public PostService(){
		this.postRepository = new PostRepository();
		this.validationUtil = new ValidationUtil(postRepository);
	}

	public Post createPost(String title){
		try{
			validationUtil.validateDuplicate(title);
			Post post = new Post(IDGenerator.generateId(), title);
			postRepository.save(post);
			return post;
		}catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
			return null;
		}

	}

	public List<Post> findAll(){
		return postRepository.findAll();
	}

	public Post getPostById(final int postId){
		validationUtil.validatePost(postId);
		return postRepository.findPostById(postId);
	}

	public boolean deletePostById(final int postId){
		validationUtil.validatePost(postId);
		return postRepository.deletePostByid(postId);
	}

	public List<Post> getByKeyword(final String keyword){
		//return postRepository.findPostByKeyword(keyword);
		return null;
	}

	public boolean updatePost(final int postId, final String title){
		try{
			validationUtil.validatePost(postId);
			validationUtil.validateDuplicate(title);
			Post existedPost = postRepository.findPostById(postId);
			existedPost.changeTitle(title);
			postRepository.updatePost(existedPost);
			return true;
		}catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
			return false;
		}

	}
}
