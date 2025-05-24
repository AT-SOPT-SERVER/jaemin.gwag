package org.sopt.domain.post.service;

import java.util.List;

import org.sopt.domain.post.dto.PostCreateOrUpdateRequest;
import org.sopt.domain.post.dto.PostResponse;
import org.sopt.domain.post.entity.Category;
import org.sopt.domain.post.entity.PostEntity;
import org.sopt.domain.post.dto.PostListResponse;
import org.sopt.domain.post.dto.PostDetailResponse;
import org.sopt.domain.post.repository.PostRepository;
import org.sopt.domain.user.entity.UserEntity;
import org.sopt.domain.user.service.UserService;
import org.sopt.global.error.code.ErrorCode;
import org.sopt.global.error.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepository;
	private final UserService userService;

	@Transactional
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

	@Transactional(readOnly = true)
	public PostListResponse getPostAll(){
		List<PostResponse> postListResponse = postRepository.findAllByOrderByCreatedAtDesc().stream()
			.map(PostResponse::from)
			.toList();

		return PostListResponse.of(postListResponse);
	}

	public PostDetailResponse getPost(final Long postId){
		PostEntity postEntity = postRepository.findById(postId)
			.orElseThrow(()-> new BusinessException(ErrorCode.DATA_NOT_FOUND));
		return PostDetailResponse.of(postEntity);
	}

	public PostEntity getPostById(final Long postId){
		PostEntity postEntity = postRepository.findById(postId)
			.orElseThrow(()-> new BusinessException(ErrorCode.DATA_NOT_FOUND));
		return postEntity;
	}

	@Transactional
	public void deletePostById(final Long userId, final Long postId){
		PostEntity postEntity = postRepository.findById(postId)
				.orElseThrow(() -> new BusinessException(ErrorCode.DATA_NOT_FOUND));

		validateAuther(userId, postEntity);

		postRepository.deleteById(postId);
	}

	public PostListResponse getByTitle(String title) {
		List<PostResponse> postList = postRepository.findByTitleOrderByCreatedAtDesc(title).stream()
			.map(PostResponse::from)
			.toList();

		return PostListResponse.of(postList);
	}

	@Transactional
	public void updatePost(final Long userId, final Long postId, final PostCreateOrUpdateRequest postRequest) {
		PostEntity existedPostEntity = postRepository.findById(postId)
			.orElseThrow(() -> new BusinessException(ErrorCode.DATA_NOT_FOUND));

		validateAuther(userId, existedPostEntity);
		checkDuplicateTitle(postRequest.title());

		existedPostEntity.changeCategory(Category.fromName(postRequest.category()));
		existedPostEntity.changeTitle(postRequest.title());
		existedPostEntity.changeContent(postRequest.content());

		postRepository.save(existedPostEntity);
	}

	public PostListResponse getByCategory(String category){
		List<PostResponse> postResponseList = postRepository.findAllByCategoryOrderByCreatedAtDesc(Category.fromName(category)).stream()
			.map(PostResponse::from)
			.toList();

		return PostListResponse.of(postResponseList);
	}

	public PostListResponse getByUserName(String userName){
		UserEntity userEntity = userService.getUserByName(userName);

		List<PostResponse> postResponseList = postRepository.findAllByUserOrderByCreatedAtDesc(userEntity).stream()
			.map(PostResponse::from)
			.toList();

		return PostListResponse.of(postResponseList);
	}

	public void checkDuplicateTitle(String title) {
		if(postRepository.existsByTitle(title)){
			throw new BusinessException(ErrorCode.BAD_REQUEST_DATA);
		}
	}

	public void validateAuther(Long userId, PostEntity postEntity) {
		if(!postEntity.getUser().getId().equals(userId)){
			throw new BusinessException(ErrorCode.BOARD_FORBIDDEN);
		}
	}


}
