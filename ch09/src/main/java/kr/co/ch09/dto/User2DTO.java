package kr.co.ch09.dto;

import kr.co.ch09.entity.User2;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User2DTO {
    private String uid;
    private String name;
    private String birth;
    private String addr;

    public User2 toEntity(){

        return User2.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .addr(addr)
                .build();
    }
}
