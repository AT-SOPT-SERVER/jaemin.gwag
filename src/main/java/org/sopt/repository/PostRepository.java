package org.sopt.repository;

import java.util.ArrayList;
import java.util.List;

import org.sopt.domain.Post;

public class PostRepository {
	List<Post> postList = new ArrayList<>();

	public void save(Post post){
		postList.add(post);
	}

	public List<Post> findAll(){
		return postList;
	}

	public Post findPostById(int postId){
		for(Post post : postList) {
			if (post.getId() == postId){
				return post;
			}
		}
		return null;
	}

	public boolean deletePostByid(int postId){
	// 	for(Post post : postList){
	// 		if(post.getId() == postId){
	// 			postList.remove(post);
	// 			return true;
	// 		}
	// 	}
	// 	return false;
		return postList.removeIf(post->post.getId() == postId);
	}

	public List<Post> getPostByKeyword(String keyword){
		return null;
	}

	public void updatePost(Post updatedPost){
		for (int i = 0; i < postList.size(); i++) {
			if (postList.get(i).getId() == updatedPost.getId()) {
				postList.set(i, updatedPost);
			}
		}
	}

}
