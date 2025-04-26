package org.sopt.api.repository;

import java.util.List;

import org.sopt.api.domain.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {

	boolean existsByTitle(String title);

	List<PostEntity> findByTitle(String keyword);

}
