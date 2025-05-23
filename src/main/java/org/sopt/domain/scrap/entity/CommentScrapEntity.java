package org.sopt.domain.scrap.entity;

import org.sopt.domain.comment.entity.CommentEntity;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "comment_scrap")
public class CommentScrapEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "isDeleted")
	private boolean isDeleted;

	@ManyToOne(targetEntity = CommentEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id", nullable = false)
	private CommentEntity commentEntity;

	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	@Builder
	public CommentScrapEntity(boolean isDeleted, CommentEntity commentEntity, UserEntity userEntity) {
		this.isDeleted = isDeleted;
		this.commentEntity = commentEntity;
		this.userEntity = userEntity;
	}
}
