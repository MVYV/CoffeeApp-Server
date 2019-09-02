package com.mvyv.march11webapp.service;

import com.mvyv.march11webapp.domain.Role;
import com.mvyv.march11webapp.domain.User;
import com.mvyv.march11webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final JavaMailSender mailSender;

  @Autowired
  public UserService(UserRepository userRepository, JavaMailSender mailSender) {
    this.userRepository = userRepository;
    this.mailSender = mailSender;
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
    user.setPassword(hashPassword(user.getPassword()));
//    validateBeforeSave(user);
    return userRepository.save(user);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  private String hashPassword(String plainTextPassword){
    return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
  }

  private void validateBeforeSave(User user) throws Exception {
    // TODO: delete test email check
    if (!"testuserswebapp@gmail.com".equals(user.getEmail())) {
      Optional<User> userOptional = getByEmail(user.getEmail());
      if (userOptional.isPresent()) {
        if (!user.getId().equals(userOptional.get().getId()))
        throw new Exception("User with this email: " + user.getEmail() + " is already exist");
      }
    }
  }

  public void banUser(User user) throws Exception {
    if (user.getIsActive() == 1) {
      user.setIsActive((byte) 0);
    } else {
      user.setIsActive((byte) 1);
    }
    save(user);
  }

  public void sendEmail(String to, String subject, String text) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    helper.setTo(to);
    helper.setText(text);
    helper.setSubject(subject);

    mailSender.send(message);
  }
}
