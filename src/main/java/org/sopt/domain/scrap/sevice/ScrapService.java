package org.sopt.domain.scrap.sevice;

import org.sopt.domain.comment.entity.CommentEntity;
import org.sopt.domain.comment.service.CommentService;
import org.sopt.domain.post.entity.PostEntity;
import org.sopt.domain.post.service.PostService;
import org.sopt.domain.scrap.entity.CommentScrapEntity;
import org.sopt.domain.scrap.entity.PostScrapEntity;
import org.sopt.domain.scrap.repository.CommentScrapRepository;
import org.sopt.domain.scrap.repository.PostScrapRepository;
import org.sopt.domain.user.entity.UserEntity;
import org.sopt.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ScrapService {
	private final PostScrapRepository postScrapRepository;
	private final CommentScrapRepository commentScrapRepository;
	private final UserService userService;
	private final CommentService commentService;
	private final PostService postService;

	public void createCommentScrap(Long commentId, Long userId){
		UserEntity userEntity = userService.getUserById(userId);
		CommentEntity commentEntity = commentService.getCommentById(commentId);
		CommentScrapEntity commentScrapEntity = CommentScrapEntity.builder()
			.commentEntity(commentEntity)
			.userEntity(userEntity)
			.build();
		commentScrapRepository.save(commentScrapEntity);
	}

	public void createPostScrap(Long postId, Long userId){
		UserEntity userEntity = userService.getUserById(userId);
		PostEntity postEntity = postService.getPostById(postId);
		PostScrapEntity commentScrapEntity = PostScrapEntity.builder()
			.userEntity(userEntity)
			.postEntity(postEntity)
			.build();
		postScrapRepository.save(commentScrapEntity);
	}
}
