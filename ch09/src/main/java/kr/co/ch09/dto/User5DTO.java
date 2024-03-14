package kr.co.ch09.dto;

import kr.co.ch09.entity.User5;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User5DTO {

    private String seq;
    private String name;
    private String gender;
    private int age;
    private String addr;


    public User5 toEntity(){

        return User5.builder()
                            .seq(seq)
                            .name(name)
                            .gender(gender)
                            .age(age)
                            .addr(addr)
                            .build();
    }


}
