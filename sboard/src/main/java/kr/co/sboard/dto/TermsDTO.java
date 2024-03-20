package kr.co.sboard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TermsDTO {

    // Terms(Entity)와는 RootConfig를 통해서 매핑
    private String terms;
    private String privacy;
    private String sms;

}