package kr.co.ch03;

import kr.co.ch03.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 날짜 : 2024/03/04
 * 이름 : 김광은
 * 내용 : Spring AOP 실습하기
 * 
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BasicService basicService = (BasicService) context.getBean("basicService");
        basicService.insert();
        basicService.select(null);
        basicService.update();
        basicService.delete();


    }
}
