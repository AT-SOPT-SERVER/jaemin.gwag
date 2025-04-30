package org.sopt.domain.post.repository;

import java.util.List;

import org.sopt.domain.post.entity.Category;
import org.sopt.domain.post.entity.PostEntity;
import org.sopt.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {

	boolean existsByTitle(String title);

	List<PostEntity> findByTitleOrderByCreatedAtDesc(String keyword);

	List<PostEntity> findAllByOrderByCreatedAtDesc();

	List<PostEntity> findAllByCategoryOrderByCreatedAtDesc(Category category);

	List<PostEntity> findAllByUserOrderByCreatedAtDesc(UserEntity user);

}
