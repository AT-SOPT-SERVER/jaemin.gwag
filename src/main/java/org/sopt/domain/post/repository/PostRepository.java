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

	List<PostEntity> findByTitle(String keyword);

	List<PostEntity> findAllByOrderByCreatedAt();

	List<PostEntity> findAllByCategory(Category category);

	List<PostEntity> findAllByUser(UserEntity user);

}
