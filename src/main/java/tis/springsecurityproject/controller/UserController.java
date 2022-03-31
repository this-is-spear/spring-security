package tis.springsecurityproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tis.springsecurityproject.infra.UserEntity;
import tis.springsecurityproject.infra.UserRepository;

@Controller
@RequestMapping("user")
public class UserController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("login")
  public String loginPage() {
    return "login";
  }

  @GetMapping("signup")
  public String signUpPage() {
    return "sign-up";
  }

  @PostMapping("signup")
  public String signUp(
    @RequestParam String username,
    @RequestParam String password,
    @RequestParam String passwordConfirm
  ) {
    if (!password.equals(passwordConfirm)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    UserEntity userEntity = new UserEntity(username, passwordEncoder.encode(password));
    UserEntity entity = userRepository.save(userEntity);
    log.info("join success {}", entity);
    return "redirect:/home";
  }
}
