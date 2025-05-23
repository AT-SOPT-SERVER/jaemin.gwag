package org.sopt.domain.comment.repository;

import java.util.List;

import org.sopt.domain.comment.entity.CommentEntity;
import org.sopt.domain.post.entity.PostEntity;
import org.sopt.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
	CommentEntity findByPostEntityAndUserEntity(PostEntity post, UserEntity userEntity);
	List<CommentEntity> findAllByPostEntity(PostEntity post);
}
