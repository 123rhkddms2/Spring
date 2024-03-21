package kr.co.sboard.config;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Getter
@Setter
@Configuration
@EnableAspectJAutoProxy
public class RootConfig {
    // 빌드 정보 객체를 주입 받기 위해 build.gradle 파일 맨 밑에 buildInfo() 실행 해야됨
    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public ModelMapper modelMapper(){                   // 매핑 작업을 편리하게 수행하기 위해 modelMapper 인스턴스를 생성(DTO <> Entity 간 변환작업)

        ModelMapper modelMapper = new ModelMapper();    // Entity의 @Setter 선언없이 바로 private 속성으로 초기화 설정

        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)      // private 필드에 접근할 수 있게함
                .setMatchingStrategy(MatchingStrategies.STRICT)                                     // 매우 엄격한 매칭 전략을 사용
                .setFieldMatchingEnabled(true);                                                     // 필드 매칭을 활성화

        return modelMapper;
    }

}