package com.mvyv.march11webapp.service;

import com.mvyv.march11webapp.domain.Comment;
import com.mvyv.march11webapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

  private final CommentRepository commentRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public List<Comment> getByProductId(Long productId) {
    return commentRepository.findAllByProductIdOOrderByCommentDate(productId);
  }

  public List<Comment> getByUserId(Long userId) {
    return commentRepository.findAllByUserIdOOrderByCommentDate(userId);
  }

  public List<Comment> getByNewsId(Long newsId) {
    return commentRepository.findAllByNewsId(newsId);
  }

  public Optional<Comment> getById(Long id) {
    return Optional.ofNullable(commentRepository.getOne(id));
  }

  public Comment save(Comment comment) {
    comment.setCommentDate(new Date());
    return commentRepository.save(comment);
  }

  public void delete(Long id) {
    commentRepository.deleteById(id);
  }
}
