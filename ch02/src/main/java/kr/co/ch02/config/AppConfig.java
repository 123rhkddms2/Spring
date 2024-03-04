package kr.co.ch02.config;

import kr.co.ch02.sub1.Greeting;
import kr.co.ch02.sub1.Hello;
import kr.co.ch02.sub1.Welcome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Bean 을 등록하기 위한 컨테이너 공간을 설정(@Bean 이 필요하다.)
@Configuration

//Component 들을 스캔하여 Bean 으로 등록(Component 를 읽는다.)
@ComponentScan(basePackages = {"kr.co.ch02"})       // kr.co.ch02에 있는 모든 @Bean 을 탐색한다.



public class AppConfig {

    // Hello.java 클래스를 참조한다.(return 이 Hello()이므로 반환값은 Hello)
    @Bean
    public Hello hello(){
        return new Hello();
    }

    // Welcome.java 클래스를 참조한다.(return 이 Welcome()이므로 반환값은 Welcome)
    @Bean(name = "welcome")
    public Welcome welcome(){
        return new Welcome();
    }

    // Greeting.java 클래스를 참조한다.(return 이 Greeting()이므로 반환값은 Greeting)
    @Bean("greeting")
    public Greeting greeting(){
        return new Greeting();
    }
}