package com.mvyv.march11webapp.controller;

import com.mvyv.march11webapp.domain.Role;
import com.mvyv.march11webapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public ResponseEntity<List<Role>> getAllRoles() {
    List<Role> roleList = new ArrayList<>();
    Role user = new Role();
    user.setId(2);
    user.setChecked(false);
    user.setRole("USER");
    Role admin = new Role();
    admin.setId(1);
    admin.setChecked(false);
    admin.setRole("ADMIN");
    roleList.add(user);
    roleList.add(admin);
    return ResponseEntity.ok(roleList);
  }
}
