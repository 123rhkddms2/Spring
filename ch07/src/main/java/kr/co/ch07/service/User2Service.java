package kr.co.ch07.service;

import kr.co.ch07.dto.User2DTO;
import kr.co.ch07.entity.board.User2;
import kr.co.ch07.repository.User2Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class User2Service {

    // 생성자 주입
    private final User2Repository repository;


    public void insertUser2(User2DTO user2DTO){
        // DTO를 Entity로 변환
        User2 user2 = user2DTO.toEntity();

        // Entity 저장(데이터베이스 Insert)
        repository.save(user2);
    }

    public User2DTO selectUser2(String uid){

        /*
            Optional
             - null값 검증처리를 손쉽게 활용하는 구조체
             - Spring JPA 조회 결과 타입
         */

        // 조회
        Optional<User2> result = repository.findById(uid);
        User2 user2 = result.get();

        // Entity를 DTO로 변환 후 리턴
        return user2.toDTO();
    }

    public List<User2DTO> selectUser2s(){
        // 전체 조회
        List<User2> user2s = repository.findAll();

        // Entity 리스트를 DTO 리스트로 변환
        /*
        // 일반적인 방식
        List<User2DTO> user2DTOs = new ArrayList<>();

        for(User2 user2 : user2s){
            user2DTOs.add(user2.toDTO());
        }
        */
        List<User2DTO> user2DTOs = user2s.stream()
                .map(entity -> User2DTO.builder()
                        .uid(entity.getUid())
                        .name(entity.getName())
                        .birth(entity.getBirth())
                        .addr(entity.getAddr())
                        .build())
                .collect(Collectors.toList());

        return user2DTOs;
    }

    public void updateUser2(User2DTO user2DTO){

        // DTO를 Entity로 변환
        User2 user2 = user2DTO.toEntity();

        // Entity 수정(데이터베이스 Update)
        repository.save(user2);
    }

    public void deleteUser2(String uid){
        // Entity 삭제(데이터베이스 Delete)
        repository.deleteById(uid);
    }
}