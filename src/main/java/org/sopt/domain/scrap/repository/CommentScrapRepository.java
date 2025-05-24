package org.sopt.domain.scrap.repository;

import org.sopt.domain.scrap.entity.CommentScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentScrapRepository extends JpaRepository<CommentScrapEntity, Long> {
}
