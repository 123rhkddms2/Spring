package kr.co.ch07.service;

import kr.co.ch07.dto.User4DTO;
import kr.co.ch07.entity.board.User4;
import kr.co.ch07.repository.User4Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class User4Service {

    // 생성자 주입
    private final User4Repository repository;


    public void insertUser4(User4DTO user4DTO){
        // DTO를 Entity로 변환
        User4 user4 = user4DTO.toEntity();

        // Entity 저장(데이터베이스 Insert)
        repository.save(user4);
    }

    public User4DTO selectUser4(String uid){

        /*
            Optional
             - null값 검증처리를 손쉽게 활용하는 구조체
             - Spring JPA 조회 결과 타입
         */

        // 조회
        Optional<User4> result = repository.findById(uid);
        User4 user4 = result.get();

        // Entity를 DTO로 변환 후 리턴
        return user4.toDTO();
    }

    public List<User4DTO> selectUser4s(){
        // 전체 조회
        List<User4> user4s = repository.findAll();

        // Entity 리스트를 DTO 리스트로 변환
        /*
        // 일반적인 방식
        List<User4DTO> user4DTOs = new ArrayList<>();

        for(User4 user4 : user4s){
            user4DTOs.add(user4.toDTO());
        }
        */
        List<User4DTO> user4DTOs = user4s.stream()
                .map(entity -> User4DTO.builder()
                        .uid(entity.getUid())
                        .name(entity.getName())
                        .gender(entity.getGender())
                        .age(entity.getAge())
                        .hp(entity.getHp())
                        .addr(entity.getAddr())
                        .build())
                .collect(Collectors.toList());

        return user4DTOs;
    }

    public void updateUser4(User4DTO user4DTO){

        // DTO를 Entity로 변환
        User4 user4 = user4DTO.toEntity();

        // Entity 수정(데이터베이스 Update)
        repository.save(user4);
    }

    public void deleteUser4(String uid){
        // Entity 삭제(데이터베이스 Delete)
        repository.deleteById(uid);
    }
}