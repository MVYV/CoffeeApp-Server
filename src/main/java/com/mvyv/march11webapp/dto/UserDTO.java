package com.mvyv.march11webapp.dto;

import com.mvyv.march11webapp.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO {

  private Long id;
  private String userName;
  private String lastName;
  private Byte isActive;
  private String avatar;
  private String country;
  private String city;
  private Date dateOfBirth;
  private String gender;
  private List<Role> roles;
}
