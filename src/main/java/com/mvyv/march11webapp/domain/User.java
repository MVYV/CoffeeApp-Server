package com.mvyv.march11webapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "USERS")
public class User implements Serializable {

  public User(User user) {
    id = user.getId();
    email = user.getEmail();
    password = user.getPassword();
    userName = user.getUserName();
    lastName = user.getLastName();
    isActive = user.getIsActive();
    roles = user.getRoles();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "user_id", unique = true)
  private Long id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "name")
  private String userName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "active")
  private Byte isActive;

//  @Column(name = "avatar")
//  private String avatar;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles;

}
