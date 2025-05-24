package org.sopt.domain.post.service;

import java.util.List;

import org.sopt.domain.post.dto.PostCreateOrUpdateRequest;
import org.sopt.domain.post.entity.Category;
import org.sopt.domain.post.entity.PostEntity;
import org.sopt.domain.post.dto.PostListResponse;
import org.sopt.domain.post.dto.PostResponse;
import org.sopt.domain.post.repository.PostRepository;
import org.sopt.domain.user.service.UserService;
import org.sopt.global.error.code.ErrorCode;
import org.sopt.global.error.exception.BusinessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepository;
	private final UserService userService;

	public void createPost(Long userId, PostCreateOrUpdateRequest postRequest){
		checkDuplicateTitle(postRequest.title());

		PostEntity postEntity = PostEntity.builder()
			.title(postRequest.title())
			.content(postRequest.content())
			.user(userService.getUserById(userId))
			.category(Category.fromName(postRequest.category()))
			.build();

		postRepository.save(postEntity);
	}

	public List<PostEntity> findAll(){
		return postRepository.findAll();
	}

	public PostResponse getPost(final Long postId){
		PostEntity postEntity = postRepository.findById(postId)
			.orElseThrow(()-> new BusinessException(ErrorCode.DATA_NOT_FOUND));
		return PostResponse.of(postEntity);
	}

	public PostEntity getPostById(final Long postId){
		PostEntity postEntity = postRepository.findById(postId)
			.orElseThrow(()-> new BusinessException(ErrorCode.DATA_NOT_FOUND));
		return postEntity;
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
