package kr.co.ch07.entity.board;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.ch07.dto.User2DTO;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity                     // 엔티티 객체 정의
@Table(name = "user2")      // 매핑 테이블 설정
public class User2 {

    @Id                     // PK 컬럼 설정
    private String uid;

    @Column(name = "name")  // 매핑 컬럼 설정
    private String name;

    @Column(name = "birth")
    private String birth;

    @Column(name = "addr")
    private String addr;

    // DTO 변환 메서드
    public User2DTO toDTO(){
        return User2DTO.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .addr(addr).build();
    }
}