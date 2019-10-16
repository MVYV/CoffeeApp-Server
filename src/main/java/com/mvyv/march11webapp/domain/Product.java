package com.mvyv.march11webapp.domain;

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
@Table(name = "PRODUCTS")
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "product_id", unique = true)
  private Long id;

  @Column(name = "name")
  private String productName;

  @Column(name = "type")
  private String productType;

  @Column(name = "image")
  private String image;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Double price;

  @Column(name = "created_on")
  private Date productDate;

  @Column(name = "modified_on")
  private Date productModificationDate;

  @Column(name = "language")
  private String language;

}
