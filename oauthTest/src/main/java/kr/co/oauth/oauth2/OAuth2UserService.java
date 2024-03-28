package kr.co.oauth.oauth2;

import kr.co.oauth.entity.User;
import kr.co.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        String accessToken = userRequest.getAccessToken().getTokenValue();
        log.info("loadUser...1 : " + accessToken);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        log.info("loadUser...2 : " + provider);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("loadUser...3 : " + oAuth2User);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        log.info("loadUser...4 : " + attributes);

        KakaoInfo kakaoInfo = new KakaoInfo(attributes);

        // 회원가입 처리
        String nick = kakaoInfo.getNickName();
        String image = kakaoInfo.getProfileImage();
        String pro   = kakaoInfo.getProvider();

        User user = User.builder()
                        .uid(pro+"_"+nick)
                        .name(nick)
                        .build();

        userRepository.save(user);

        return user;
    }
}
