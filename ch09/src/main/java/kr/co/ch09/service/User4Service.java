package kr.co.ch09.service;

import kr.co.ch09.dto.User4DTO;
import kr.co.ch09.entity.User4;
import kr.co.ch09.repository.User4Repository;
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
public class User4Service {

    private final User4Repository user4Repository;

    public void insertUser4(User4DTO user4DTO){
        User4 user4 = user4DTO.toEntity();
        user4Repository.save(user4);
    }

    public User4DTO selectUser4(String uid){
        User4 user4 = user4Repository.findById(uid).get();
        return user4.toDTO();
    }

    public List<User4DTO> selectUser4s(){
        return user4Repository.findAll()
                .stream()
                .map(entity->User4DTO.builder()
                                .uid(entity.getUid())
                                .name(entity.getName())
                                .gender(entity.getGender())
                                .age(entity.getAge())
                                .hp(entity.getHp())
                                .addr(entity.getAddr())
                                .build())
                .collect(Collectors.toList());
    }

    public User4DTO updateUser4(User4DTO user4DTO){
        
        // 수정
        user4Repository.save(user4DTO.toEntity());
        
        // 수정한 사용자 조회/반환
        Optional<User4> result = user4Repository.findById(user4DTO.getUid());
        return result.get().toDTO();

    }

    public ResponseEntity deleteUser4(String uid){

        // 삭제 전 삭제할 사용자 조회
        Optional<User4> optUser4 = user4Repository.findById(uid);

        if(optUser4.isPresent()){
            // 사용자가 존재하면 삭제 후 삭제한 사용자 정보 ResponseEntity로 반환
            user4Repository.deleteById(uid);
            return ResponseEntity
                    .ok()
                    .body(optUser4.get());
        }else{
            // 사용자가 존재하지 않으면 NOT_FOUND 응답데이터와 user not found 메세지
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("user not found");
        }
    }
}
