package kr.co.ch02.sub2;

import org.springframework.stereotype.Component;

// ComponentScan 이 읽었고, Bean 이 되었다.
@Component
public class Ram {

    // 단순한 메서드 함수
    public void show(){
        System.out.println("RAM : Samsung DDR5 4GB");
    }
}
