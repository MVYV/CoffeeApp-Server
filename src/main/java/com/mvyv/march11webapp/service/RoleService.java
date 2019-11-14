package com.mvyv.march11webapp.service;

import com.mvyv.march11webapp.domain.Role;
import com.mvyv.march11webapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

  private final RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public List<Role> getAll() {
    return roleRepository.findAll();
  }

  public Role getByName(String role) {
    return roleRepository.findByRole(role);
  }
}
