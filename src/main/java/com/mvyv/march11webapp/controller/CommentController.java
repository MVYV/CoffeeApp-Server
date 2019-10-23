package com.mvyv.march11webapp.controller;

import com.mvyv.march11webapp.domain.Comment;
import com.mvyv.march11webapp.domain.User;
import com.mvyv.march11webapp.dto.CommentDTO;
import com.mvyv.march11webapp.service.CommentService;
import com.mvyv.march11webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

  private final CommentService commentService;
  private final UserService userService;

  @Autowired
  public CommentController(CommentService commentService, UserService userService) {
    this.commentService = commentService;
    this.userService = userService;
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<List<CommentDTO>> getCommentsByProductId(@PathVariable("id") Long productId) {
    return ResponseEntity.ok(map(commentService.getByProductId(productId)));
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<CommentDTO>> getCommentsByUserId(@PathVariable("id") Long userId) {
    return ResponseEntity.ok(map(commentService.getByUserId(userId)));
  }

  @GetMapping("/news/{id}")
  public ResponseEntity<List<CommentDTO>> getCommentsByNewsId(@PathVariable("id") Long newsId) {
    return ResponseEntity.ok(map(commentService.getByNewsId(newsId)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommentDTO> getComment(@PathVariable("id") Long id) {
    Optional<Comment> optionalComment = commentService.getById(id);
    if (optionalComment.isPresent()) {
      return ResponseEntity.ok(map(optionalComment.get()));
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<CommentDTO> saveComment(@RequestBody Comment comment) {
    return ResponseEntity.ok(map(commentService.save(comment)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CommentDTO> updateComment(@PathVariable("id") Long id, @RequestBody CommentDTO comment) {
    Optional<Comment> optionalComment = commentService.getById(id);
    if (optionalComment.isPresent()) {
      Comment found = optionalComment.get();
      merge(found, comment);
      return ResponseEntity.ok(map(commentService.save(found)));
    }
    return ResponseEntity.notFound().build();
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
    commentService.delete(id);
    return ResponseEntity.noContent().build();
  }

  private List<CommentDTO> map(List<Comment> commentList) {
    return commentList.stream().map(this::map).collect(Collectors.toList());
  }

  private CommentDTO map(Comment domain) {
    CommentDTO dto = new CommentDTO();
    dto.setCommentText(domain.getCommentText());
    dto.setId(domain.getId());
    dto.setNewsId(domain.getNewsId());
    dto.setProductId(domain.getProductId());
    dto.setUserId(domain.getUserId());
    dto.setCommentDate(domain.getCommentDate());
    Optional<User> user = userService.getById(domain.getUserId());
    user.ifPresent(u -> dto.setFullName(u.getUserName() + " " + u.getLastName()));

    return dto;
  }

  private void merge(Comment dbComment, CommentDTO update) {
    dbComment.setCommentText(update.getCommentText());
  }
}
