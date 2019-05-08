package com.mvyv.march11webapp.service;

import com.mvyv.march11webapp.domain.Role;
import com.mvyv.march11webapp.domain.User;
import com.mvyv.march11webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public Optional<User> getById(Long id) {
    return Optional.ofNullable(userRepository.getOne(id));
  }

  public Optional<User> getByEmail(String email) {
    return Optional.ofNullable(userRepository.findByEmail(email));
  }

  public User save(User user) throws Exception {
    user.setActive(1);
    Role role = new Role();
    role.setRole("USER");
    List<Role> roles = new ArrayList<>();
    roles.add(role);
    user.setRoles(roles);
//    user.setRole(role);
    validateBeforeSave(user);
    return userRepository.save(user);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  private void validateBeforeSave(User user) throws Exception {
    // TODO: delete test email check
    if (!"testuserswebapp@gmail.com".equals(user.getEmail())) {
      Optional<User> userOptional = getByEmail(user.getEmail());
      if (userOptional.isPresent()) {
        throw new Exception("User with this email is already exist");
      }
    }
  }
}
