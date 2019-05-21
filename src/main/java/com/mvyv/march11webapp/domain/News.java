package com.mvyv.march11webapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
//@Table(name = "NEWS")
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
//  @Column(name = "news_id", unique = true)
  private Long id;

//  @Column(name = "title")
  private String title;

//  @Column(name = "text")
  private String text;

//  @Column(name = "sub_text")
  private String subText;
}
