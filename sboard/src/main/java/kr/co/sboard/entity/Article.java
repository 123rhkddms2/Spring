package kr.co.sboard.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Builder.Default
    private int parent = 0;

    @Builder.Default
    private int comment = 0;
    private String cate;
    private String title;
    private String content;
    private String writer;

    @Builder.Default
    private int file = 0;

    @Builder.Default
    private int hit = 0;

    private String regip;

    @CreationTimestamp
    private LocalDateTime rdate;

    @OneToMany(mappedBy = "ano")    // 게시글과 파일 엔티티 간의 일대다 관계를 매핑합니다. ano 필드는 File 엔티티의 ano 필드와 매핑됩니다.
    private List<File> fileList;    //  게시글에 첨부된 파일 목록을 나타내는 필드입니다. 이 필드는 일대다 관계 매핑을 통해 관련 파일 엔티티들을 가질 수 있습니다.



}