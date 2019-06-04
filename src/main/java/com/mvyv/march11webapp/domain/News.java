package com.mvyv.march11webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "NEWS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class News implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "news_id", unique = true)
  private Long id;

  @Column(name = "title")
  private String newsTitle;

  @Column(name = "text")
  private String newsText;

  @Column(name = "sub_text")
  private String newsSubText;
}
