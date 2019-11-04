package com.mvyv.march11webapp.controller;

import com.mvyv.march11webapp.domain.User;
import com.mvyv.march11webapp.dto.MailDTO;
import com.mvyv.march11webapp.dto.UserDTO;
import com.mvyv.march11webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<String>> getAllItems() throws Exception {
    List<String> list = Arrays.asList("1", "2", "3");
    Optional<User> optionalUser = userService.getById(2L);
    if (optionalUser.isPresent()) {
      userService.banUser(optionalUser.get());
    }
    return ResponseEntity.ok(list);
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAll() {
    return ResponseEntity.ok(map(userService.getAll()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable("id") Long id) {
    Optional<User> userOptional = userService.getById(id);
    if (userOptional.isPresent()) {
      return ResponseEntity.ok(map(userOptional.get()));
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<User> getByEmail(@PathVariable("email") String email) {
    Optional<User> userOptional = userService.getByEmail(email);
    if (userOptional.isPresent()) {
      return ResponseEntity.ok(userOptional.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<User> addNewUser(@RequestBody User user) throws Exception { ;
    return ResponseEntity.ok(userService.save(user));
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
    Optional<User> optionalUser = userService.getById(id);
    if (optionalUser.isPresent()) {
      User found = optionalUser.get();
      if (user.getPassword() != null) found.setPassword(user.getPassword());
      merge(found, user);
      return ResponseEntity.ok(userService.save(found));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/sendMail")
  public ResponseEntity<Void> sendMail(@RequestBody MailDTO mailDTO) throws MessagingException {
    Optional<User> user = userService.getById(mailDTO.getMailTo());
    if (user.isPresent()) {
      userService.sendEmail(user.get().getEmail(), mailDTO.getMailSubject(), mailDTO.getMailText());
    }
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> banUser(@PathVariable("id") Long id) throws Exception {
    Optional<User> optionalUser = userService.getById(id);
    if (optionalUser.isPresent()) {
      userService.banUser(optionalUser.get());
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/check-mail")
  public ResponseEntity<Integer> checkEmailBeforeSave(@RequestBody MailDTO mailDTO) throws MessagingException {
    int code = userService.beforeSave();
    userService.sendEmail(mailDTO.getMailToAddress(), "Confirm registration",
            "Please enter this code to complete Your registration: " + code);
    return ResponseEntity.ok(code);
  }

  private List<UserDTO> map(List<User> userList) {
    return userList.stream().map(this::map).collect(Collectors.toList());
  }

  private UserDTO map(User user) {
    UserDTO dto = new UserDTO();
    dto.setAvatar(user.getAvatar());
    dto.setCity(user.getCity());
    dto.setCountry(user.getCountry());

    dto.setDateOfBirth(user.getDateOfBirth());
    dto.setGender(user.getGender());
    dto.setId(user.getId());
    dto.setIsActive(user.getIsActive());
    dto.setLastName(user.getLastName());
    dto.setRoles(user.getRoles());
    dto.setUserName(user.getUserName());

    return dto;
  }


  private void merge(User dbUser, User update) {
    dbUser.setUserName(update.getUserName());
    dbUser.setPassword(update.getPassword());
    dbUser.setLastName(update.getLastName());
    if (update.getEmail() != null) dbUser.setEmail(update.getEmail());
    dbUser.setRoles(update.getRoles());
    dbUser.setIsActive(update.getIsActive());
    dbUser.setAvatar(update.getAvatar());
    dbUser.setCountry(update.getCountry());
    dbUser.setCity(update.getCity());
    dbUser.setDateOfBirth(update.getDateOfBirth());
    dbUser.setGender(update.getGender());
  }
}
