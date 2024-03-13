package kr.co.ch07.dto;

import kr.co.ch07.entity.board.User1;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User5DTO {

    private String uid;
    private String name;
    private String birth;
    private String hp;
    private int age;

    // Entity 변환 메서드
    public User1 toEntity(){
        return User1.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .age(age)
                .build();
    }

}