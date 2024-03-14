package kr.co.ch09.service;

import kr.co.ch09.dto.User3DTO;
import kr.co.ch09.entity.User3;
import kr.co.ch09.repository.User3Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class User3Service {

    private final User3Repository user3Repository;

    public void insertUser3(User3DTO user3DTO){
        User3 user3 = user3DTO.toEntity();
        user3Repository.save(user3);
    }

    public User3DTO selectUser3(String uid){
        User3 user3 = user3Repository.findById(uid).get();
        return user3.toDTO();
    }

    public List<User3DTO> selectUser3s(){
        return user3Repository.findAll()
                .stream()
                .map(entity->User3DTO.builder()
                                .uid(entity.getUid())
                                .name(entity.getName())
                                .birth(entity.getBirth())
                                .hp(entity.getHp())
                                .addr(entity.getAddr())
                                .build())
                .collect(Collectors.toList());
    }

    public User3DTO updateUser3(User3DTO user3DTO){
        
        // 수정
        user3Repository.save(user3DTO.toEntity());
        
        // 수정한 사용자 조회/반환
        Optional<User3> result = user3Repository.findById(user3DTO.getUid());
        return result.get().toDTO();

    }

    public ResponseEntity deleteUser3(String uid){

        // 삭제 전 삭제할 사용자 조회
        Optional<User3> optUser3 = user3Repository.findById(uid);

        if(optUser3.isPresent()){
            // 사용자가 존재하면 삭제 후 삭제한 사용자 정보 ResponseEntity로 반환
            user3Repository.deleteById(uid);
            return ResponseEntity
                    .ok()
                    .body(optUser3.get());
        }else{
            // 사용자가 존재하지 않으면 NOT_FOUND 응답데이터와 user not found 메세지
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("user not found");
        }
    }
}
