package com.mvyv.march11webapp.repository;

import com.mvyv.march11webapp.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByProductId(Long productId);

  List<Comment> findAllByUserId(Long userId);

  List<Comment> findAllByNewsId(Long newsId);
}
