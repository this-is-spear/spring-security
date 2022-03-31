package tis.springsecurityproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tis.springsecurityproject.config.AuthenticationFacade;

@Controller
@RequestMapping("home")
public class HomeController {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final AuthenticationFacade authenticationFacade;

  public HomeController(AuthenticationFacade authenticationFacade) {
    this.authenticationFacade = authenticationFacade;
  }


  @GetMapping
  public String home(Authentication authentication) {
    if (authentication != null) {
      log.info("connected user : {}", authentication.getName());
    }
    log.info("connected user : {}", SecurityContextHolder.getContext().getAuthentication().getName());
    log.info("connected user : {}", authenticationFacade.getUserName());
    return "index";
  }
}
