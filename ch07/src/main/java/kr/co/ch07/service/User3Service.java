package kr.co.ch07.service;

import kr.co.ch07.dto.User3DTO;
import kr.co.ch07.entity.board.User3;
import kr.co.ch07.repository.User3Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class User3Service {

    // 생성자 주입
    private final User3Repository repository;


    public void insertUser3(User3DTO user3DTO){
        // DTO를 Entity로 변환
        User3 user3 = user3DTO.toEntity();

        // Entity 저장(데이터베이스 Insert)
        repository.save(user3);
    }

    public User3DTO selectUser3(String uid){

        /*
            Optional
             - null값 검증처리를 손쉽게 활용하는 구조체
             - Spring JPA 조회 결과 타입
         */

        // 조회
        Optional<User3> result = repository.findById(uid);
        User3 user3 = result.get();

        // Entity를 DTO로 변환 후 리턴
        return user3.toDTO();
    }

    public List<User3DTO> selectUser3s(){
        // 전체 조회
        List<User3> user3s = repository.findAll();

        // Entity 리스트를 DTO 리스트로 변환
        /*
        // 일반적인 방식
        List<User3DTO> user3DTOs = new ArrayList<>();

        for(User3 user3 : user3s){
            user3DTOs.add(user3.toDTO());
        }
        */
        List<User3DTO> user3DTOs = user3s.stream()
                .map(entity -> User3DTO.builder()
                        .uid(entity.getUid())
                        .name(entity.getName())
                        .birth(entity.getBirth())
                        .hp(entity.getHp())
                        .addr(entity.getAddr())
                        .build())
                .collect(Collectors.toList());

        return user3DTOs;
    }

    public void updateUser3(User3DTO user3DTO){

        // DTO를 Entity로 변환
        User3 user3 = user3DTO.toEntity();

        // Entity 수정(데이터베이스 Update)
        repository.save(user3);
    }

    public void deleteUser3(String uid){
        // Entity 삭제(데이터베이스 Delete)
        repository.deleteById(uid);
    }
}