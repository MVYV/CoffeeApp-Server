package com.mvyv.march11webapp.controller;

import com.mvyv.march11webapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/role")
public class RoleController {

  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public ResponseEntity<Set<String>> getAllRoles() {
    Set<String> roles = new TreeSet<>();
    roleService.getAll().forEach(r -> roles.add(r.getRole()));
    return ResponseEntity.ok(roles);
  }
}
