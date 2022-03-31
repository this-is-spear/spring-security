package tis.springsecurityproject.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import tis.springsecurityproject.infra.NaverOauth2Service;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final NaverOauth2Service naverOauth2Service;

  public WebSecurityConfig(UserDetailsService userDetailsService, NaverOauth2Service naverOauth2Service) {
    this.userDetailsService = userDetailsService;
    this.naverOauth2Service = naverOauth2Service;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
//    auth.inMemoryAuthentication()
//      .withUser("user")
//      .password(passwordEncoder().encode("user"))
//      .roles("USER")
//      .and()
//      .withUser("admin")
//      .password(passwordEncoder().encode("admin"))
//      .roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
      .disable()
        .authorizeRequests()
        .antMatchers("/", "/home/**", "/user/signup")
        .permitAll()
        .anyRequest()
        .authenticated()
      .and()
        .formLogin()
        .loginPage("/user/login")
        .defaultSuccessUrl("/home")
        .permitAll()
      .and()
        .oauth2Login()
          .userInfoEndpoint()
          .userService(this.naverOauth2Service)
        .and()
          .defaultSuccessUrl("/home")
      .and()
        .logout()
        .logoutUrl("/user/logout")
        .logoutSuccessUrl("/home")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .permitAll()
    ;
  }

}
