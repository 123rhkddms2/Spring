package kr.co.ch06.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class User5DTO {
    public String getSeq_user5() {
        return seq;
    }

    private String seq;
    private String name;
    private String gender;
    private int age;
    private String addr;
}