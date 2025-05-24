package org.sopt.domain.comment.entity;

import org.sopt.domain.post.entity.PostEntity;
import org.sopt.domain.user.entity.UserEntity;
import org.sopt.global.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class CommentEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "content")
	private String content;

	@ManyToOne(targetEntity = PostEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private PostEntity postEntity;

	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	@Builder
	public CommentEntity(String content, PostEntity postEntity, UserEntity userEntity) {
		this.content = content;
		this.postEntity = postEntity;
		this.userEntity = userEntity;
	}

	public void changeContent(String content){
		this.content = content;
	}
}
