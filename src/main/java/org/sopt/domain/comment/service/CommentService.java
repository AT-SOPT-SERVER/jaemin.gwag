package org.sopt.domain.comment.service;

import java.util.List;

import org.sopt.domain.comment.dto.request.CreateCommentRequest;
import org.sopt.domain.comment.dto.request.UpdateCommentRequest;
import org.sopt.domain.comment.dto.response.GetCommentListResposne;
import org.sopt.domain.comment.dto.response.GetCommentResponse;
import org.sopt.domain.comment.entity.CommentEntity;
import org.sopt.domain.comment.repository.CommentRepository;
import org.sopt.domain.post.entity.PostEntity;
import org.sopt.domain.post.service.PostService;
import org.sopt.domain.user.entity.UserEntity;
import org.sopt.domain.user.service.UserService;
import org.sopt.global.error.code.ErrorCode;
import org.sopt.global.error.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostService postService;
	private final UserService userService;

	@Transactional
	public void createComment(Long userId, Long postId, CreateCommentRequest createCommentRequest) {
		PostEntity postEntity = postService.getPostById(postId);
		UserEntity userEntity = userService.getUserById(userId);
		CommentEntity commentEntity = CommentEntity.builder()
			.postEntity(postEntity)
			.content(createCommentRequest.content())
			.userEntity(userEntity)
			.build();
		commentRepository.save(commentEntity);
	}

	@Transactional
	public void updateComment(Long userId ,Long postId, UpdateCommentRequest updateCommentRequest) {
		PostEntity postEntity = postService.getPostById(postId);
		UserEntity userEntity = userService.getUserById(userId);
		CommentEntity commentEntity = commentRepository.findByPostEntityAndUserEntity(postEntity, userEntity);
		if(commentEntity.getUserEntity().getId() != userId){
			throw new BusinessException(ErrorCode.BOARD_FORBIDDEN);
		}
		commentEntity.changeContent(updateCommentRequest.content());
		commentRepository.save(commentEntity);
	}

	@Transactional(readOnly = true)
	public GetCommentListResposne getAllCommentList(Long postId){
		PostEntity postEntity = postService.getPostById(postId);
		List<CommentEntity> commentEntityList = commentRepository.findAllByPostEntity(postEntity);
		List<GetCommentResponse> getCommentResponse = commentEntityList.stream()
			.map(GetCommentResponse::of)
			.toList();
		return GetCommentListResposne.of(getCommentResponse);
	}

	@Transactional
	public void deleteComment(Long userId, Long postId) {
		PostEntity postEntity = postService.getPostById(postId);
		UserEntity userEntity = userService.getUserById(userId);
		CommentEntity commentEntity = commentRepository.findByPostEntityAndUserEntity(postEntity, userEntity);
		if(commentEntity.getUserEntity().getId() != userId){
			throw new BusinessException(ErrorCode.BOARD_FORBIDDEN);
		}
		commentRepository.delete(commentEntity);
	}

	public CommentEntity getCommentById(Long commentId) {
		return commentRepository.findById(commentId)
			.orElseThrow(() -> new BusinessException(ErrorCode.DATA_NOT_FOUND));
	}
}
