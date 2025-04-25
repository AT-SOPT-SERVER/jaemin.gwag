package org.sopt.api.service;

import java.util.List;

import org.sopt.api.domain.PostEntity;
import org.sopt.api.dto.PostListResponse;
import org.sopt.api.dto.PostResponse;
import org.sopt.api.repository.PostRepository;
import org.sopt.global.error.code.ErrorCode;
import org.sopt.global.error.exception.BusinessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepository;


	public void createPost(final String title){
		checkDuplicateTitle(title);
		PostEntity postEntity = PostEntity.builder()
			.title(title)
			.build();
		postRepository.save(postEntity);
	}

	public List<PostEntity> findAll(){
		return postRepository.findAll();
	}

	public PostResponse getPostById(final Long postId){
		PostEntity postEntity = postRepository.findById(postId)
			.orElseThrow(()-> new BusinessException(ErrorCode.DATA_NOT_FOUND));
		return PostResponse.of(postEntity);
	}

	public void deletePostById(final Long postId){
		postRepository.deleteById(postId);
	}

	public PostListResponse getByKeyword(String keyword) {
		List<PostResponse> postList = postRepository.findByTitle(keyword).stream()
			.map(PostResponse::from)
			.toList();

		return PostListResponse.of(postList);
	}

	public void updatePost(final Long postId, final String title) {
		PostEntity existedPostEntity = postRepository.findById(postId)
			.orElseThrow(() -> new BusinessException(ErrorCode.DATA_NOT_FOUND));
		checkDuplicateTitle(title);
		existedPostEntity.changeTitle(title);
		postRepository.save(existedPostEntity);
	}

	public void checkDuplicateTitle(String title) {
		if(postRepository.existsByTitle(title)){
			throw new BusinessException(ErrorCode.BAD_REQUEST_DATA);
		}
	}
}
