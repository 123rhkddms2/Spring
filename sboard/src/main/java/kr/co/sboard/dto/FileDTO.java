package kr.co.sboard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FileDTO {

    private int fno;                // 파일 번호
    private int ano;                // 게시물 번호
    private String oName;           // 원본 파일 이름
    private String sName;           // 저장된 파일 이름
    private int download;           // 다운로드 횟수
    private LocalDateTime rdate;    // 생성일자

}
