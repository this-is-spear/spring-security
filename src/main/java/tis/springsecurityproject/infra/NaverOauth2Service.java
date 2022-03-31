package tis.springsecurityproject.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class NaverOauth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final UserRepository userRepository;

  public NaverOauth2Service(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String usernameAttributeName = userRequest
      .getClientRegistration()
      .getProviderDetails()
      .getUserInfoEndpoint()
      .getUserNameAttributeName();

    log.info("{} oath2 user : {}", this.getClass().getSimpleName(), oAuth2User.getAttributes().toString());
    return oAuth2User;
  }
}
