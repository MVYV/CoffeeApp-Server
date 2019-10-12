package com.mvyv.march11webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "CONTACT_INFORMATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContactInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", unique = true)
  private Byte id;

  @Column(name = "information")
  private String contactInfo;
}
