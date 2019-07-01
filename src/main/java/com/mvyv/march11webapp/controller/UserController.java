package com.mvyv.march11webapp.controller;

import com.mvyv.march11webapp.domain.User;
import com.mvyv.march11webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

  private final JavaMailSender mailSender;
  private final UserService userService;

  @Autowired
  public UserController(UserService userService, JavaMailSender mailSender) {
    this.userService = userService;
    this.mailSender = mailSender;
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<String>> getAllItems() throws Exception {
    List<String> list = Arrays.asList("1", "2", "3");
    Optional<User> optionalUser = userService.getById(3L);
    if (optionalUser.isPresent()) {
      userService.banUser(optionalUser.get());
    }
    return ResponseEntity.ok(list);
  }

  @GetMapping("/")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<User>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<User> getById(@PathVariable("id") Long id) {
    Optional<User> userOptional = userService.getById(id);
    if (userOptional.isPresent()) {
      return ResponseEntity.ok(userOptional.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<User> addNewUser(@RequestBody User user) throws Exception {
    return ResponseEntity.ok(userService.save(user));
  }

  @PutMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
    Optional<User> optionalUser = userService.getById(id);
    if (optionalUser.isPresent()) {
      User found = optionalUser.get();
      merge(found, user);
      return ResponseEntity.ok(userService.save(found));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/sendMail")
  public ResponseEntity<Void> sendMail() {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);

      helper.setTo("yuriyvalkiv@yahoo.com");
      helper.setText("How are you?");
      helper.setSubject("Hi");

      mailSender.send(message);
    } catch (MessagingException e) {
      e.printStackTrace();
    }

    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Void> banUser(@PathVariable("id") Long id) throws Exception {
    Optional<User> optionalUser = userService.getById(id);
    if (optionalUser.isPresent()) {
      userService.banUser(optionalUser.get());
    }
    return ResponseEntity.noContent().build();
  }


  private void merge(User dbUser, User update) {
    dbUser.setUserName(update.getUserName());
    dbUser.setPassword(update.getPassword());
    dbUser.setLastName(update.getLastName());
    dbUser.setEmail(update.getEmail());
    dbUser.setRoles(update.getRoles());
    dbUser.setIsActive(update.getIsActive());
    dbUser.setAvatar(update.getAvatar());
  }
}
