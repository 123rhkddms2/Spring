package kr.co.ch09.dto;

import kr.co.ch09.entity.User3;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User3DTO {
    private String uid;
    private String name;
    private String birth;
    private String hp;
    private String addr;

    public User3 toEntity(){

        return User3.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .hp(hp)
                .addr(addr)
                .build();
    }
}
