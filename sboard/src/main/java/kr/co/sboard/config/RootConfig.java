// 이 코드는 Spring 프레임워크에서 사용되는 설정 클래스이다.
// 이 클래스는 ModelMapper 라이브러리를 사용하여 DTO(Data Transfer Object)와 Entity 간의 매핑을 위한 빈을 정의합니다.
// Entity에서 setter를 안쓰는게 좋음, 오류때문에 setter의 강제로 써야하기때문에 setter선언 대신 해당방법을 채택

package kr.co.sboard.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {


    @Bean
    public ModelMapper modelMapper(){

        // Entity의 @Setter 선언없이 바로 private 속성으로 초기화 설정
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        return modelMapper;
    }


}