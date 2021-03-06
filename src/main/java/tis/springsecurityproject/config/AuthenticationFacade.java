package tis.springsecurityproject.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
  public Authentication getUserName() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}
