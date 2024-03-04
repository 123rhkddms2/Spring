package kr.co.ch02.sub2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// ComponentScan 이 읽었고, Bean 이 되었다.
@Component("com")
public class Computer {

    // 필드주입
    @Autowired
    private Cpu cpu;

    // 생성자 주입
    private Ram ram;

    @Autowired
    public Computer(Ram ram){
        this.ram = ram;
    }

    // 세터 주입                            // 스프링 컨테이너가 해당 클래스의 빈을 생성할 때
    private Hdd hdd;                       // 필요한 의존성을 주입하도록 지정하는 역할을 합니다.

    @Autowired
    public void setHdd(Hdd hdd) {
        this.hdd = hdd;
    }

    public void show(){
        cpu.show();
        ram.show();
        hdd.show();
    }
}