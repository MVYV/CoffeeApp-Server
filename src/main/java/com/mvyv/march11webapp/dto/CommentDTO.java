package com.mvyv.march11webapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDTO {

  private Long id;
  private String commentText;
  private Long productId;
  private Long newsId;
  private String fullName;
  private Long userId;
  private Date commentDate;
}
