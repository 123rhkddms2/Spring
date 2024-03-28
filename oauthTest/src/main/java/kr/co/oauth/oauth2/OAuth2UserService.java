package kr.co.oauth.oauth2;

import kr.co.oauth.entity.User;
import kr.co.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("loadUser...1 : " + userRequest);

        String accessToken = userRequest.getAccessToken().getTokenValue();
        log.info("loadUser...2 : " + accessToken);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        log.info("loadUser...3 : " + provider);

        OAuth2User oauth2User = super.loadUser(userRequest);
        log.info("loadUser...4 : " + oauth2User);

        Map<String, Object> attributes = oauth2User.getAttributes();
        log.info("loadUser...5 : " + attributes);

        /*
        if(provider.equals("kakao")){
        }else if(provider.equals("google")){
        }*/

        // 사용자 확인 및 회원가입 처리
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        // 사용자 확인
        Optional<User> optUser = userRepository.findById(email);

        User user = null;

        if(optUser.isEmpty()){
            // 회원가입
            user = User.builder()
                    .uid(email)
                    .name(name)
                    .provider(provider)
                    .build();

            userRepository.save(user);

        }else{
            user = optUser.get();
        }
        return user;
    }
}