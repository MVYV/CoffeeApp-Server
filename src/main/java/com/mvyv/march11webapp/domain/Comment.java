package com.mvyv.march11webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "COMMENTS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", unique = true)
  private Long id;

  @Column(name = "text")
  private String commentText;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "news_id")
  private Long newsId;

  @Column(name = "date")
  private Date commentDate;

}
