package kr.co.sboard.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {

    private int no;
    private int parent;
    private int comment;
    private String cate;
    private String title;
    private String content;
    private int file;
    private int hit;
    private String writer;
    private String regip;
    private LocalDateTime rdate;

    private List<MultipartFile> files;      // 업로드 된 파일의 목록을 나타내는 필드(게시물에 여러파일 첨부 가능하기때문에 리스트 선언)

    private UserDTO user;                   // 게시물 작성자 정보를 나타내는 필드(UserDTO 객체 형태로 저장)
    private List<FileDTO> fileList;         // 업로드 된 파일에 대한 메타 정보를 저장하는 필드(여러파일의 메타정보를 저장가능하기 때문에 리스트 선언)

}