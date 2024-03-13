package kr.co.ch07.service;

import kr.co.ch07.dto.User5DTO;
import kr.co.ch07.entity.board.User5;
import kr.co.ch07.repository.User5Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class User5Service {

    // 생성자 주입
    private final User5Repository repository;


    public void insertUser5(User5DTO user5DTO){
        // DTO를 Entity로 변환
        User5 user5 = user5DTO.toEntity();

        // Entity 저장(데이터베이스 Insert)
        repository.save(user5);
    }

    public User5DTO selectUser5(String seq){

        /*
            Optional
             - null값 검증처리를 손쉽게 활용하는 구조체
             - Spring JPA 조회 결과 타입
         */

        // 조회
        Optional<User5> result = repository.findById(seq);
        User5 user5 = result.get();

        // Entity를 DTO로 변환 후 리턴
        return user5.toDTO();
    }

    public List<User5DTO> selectUser5s(){
        // 전체 조회
        List<User5> user5s = repository.findAll();

        // Entity 리스트를 DTO 리스트로 변환
        /*
        // 일반적인 방식
        List<User5DTO> user5DTOs = new ArrayList<>();

        for(User5 user5 : user5s){
            user5DTOs.add(user5.toDTO());
        }
        */
        List<User5DTO> user5DTOs = user5s.stream()
                .map(entity -> User5DTO.builder()
                        .seq(entity.getSeq())
                        .name(entity.getName())
                        .gender(entity.getGender())
                        .age(entity.getAge())
                        .addr(entity.getAddr())
                        .build())
                .collect(Collectors.toList());

        return user5DTOs;
    }

    public void updateUser5(User5DTO user5DTO){

        // DTO를 Entity로 변환
        User5 user5 = user5DTO.toEntity();

        // Entity 수정(데이터베이스 Update)
        repository.save(user5);
    }

    public void deleteUser5(String seq){
        // Entity 삭제(데이터베이스 Delete)
        repository.deleteById(seq);
    }
}