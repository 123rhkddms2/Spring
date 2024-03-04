package kr.co.ch02.sub2;

import org.springframework.stereotype.Component;

// ComponentScan 이 읽는다.
@Component
public class Cpu {

    // 단순한 메서드 함수
    public void show(){
        System.out.println("CPU : Intel i7");
    }
}