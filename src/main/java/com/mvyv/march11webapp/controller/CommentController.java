package com.mvyv.march11webapp.controller;

import com.mvyv.march11webapp.domain.Comment;
import com.mvyv.march11webapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("comment")
public class CommentController {

  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<List<Comment>> getCommentsByProductId(@PathVariable("id") Long productId) {
    return ResponseEntity.ok(commentService.getByProductId(productId));
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable("id") Long userId) {
    return ResponseEntity.ok(commentService.getByProductId(userId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Comment> getComment(@PathVariable("id") Long id) {
    Optional<Comment> optionalComment = commentService.getById(id);
    if (optionalComment.isPresent()) {
      return ResponseEntity.ok(optionalComment.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
    return ResponseEntity.ok(commentService.save(comment));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
    commentService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
