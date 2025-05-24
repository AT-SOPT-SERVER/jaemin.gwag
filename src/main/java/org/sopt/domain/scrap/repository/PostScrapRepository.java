package org.sopt.domain.scrap.repository;

import org.sopt.domain.scrap.entity.PostScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostScrapRepository extends JpaRepository<PostScrapEntity, Long> {
}
