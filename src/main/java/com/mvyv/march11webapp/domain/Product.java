package com.mvyv.march11webapp.domain;

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
//@Table(name = "PRODUCTS")
public class Product implements Serializable {

  @Id
//  @GeneratedValue(strategy = GenerationType.SEQUENCE)
//  @Column(name = "product_id", unique = true)
  private Long id;

//  @Column(name = "name")
  private String name;

//  @Column(name = "type")
  private String type;

//  @Column(name = "image")
//  private String image;

//  @Column(name = "description")
  private String description;

//  @Column(name = "price")
  private Double price;

}
