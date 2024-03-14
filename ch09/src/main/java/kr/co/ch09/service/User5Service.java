package kr.co.ch09.service;

import kr.co.ch09.dto.User5DTO;
import kr.co.ch09.entity.User5;
import kr.co.ch09.repository.User5Repository;
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
public class User5Service {
    private final User5Repository user5Repository;

    public void insertUser5(User5DTO user5DTO){
        User5 user5 = user5DTO.toEntity();
        user5Repository.save(user5);
    };

    public User5DTO selectUser5(String seq){
        User5 user5 = user5Repository.findById(seq).get();
        return user5.toDTO();
    };

    public List<User5DTO> selsectUser5s(){
        return user5Repository.findAll()
                .stream()
                .map(entity -> User5DTO.builder()
                        .seq(entity.getSeq())
                        .name(entity.getName())
                        .gender(entity.getGender())
                        .age(entity.getAge())
                        .addr(entity.getAddr())
                        .build())
                .collect(Collectors.toList());
    };

    public User5DTO updateUser5(User5DTO user5DTO){

        // 수정
        user5Repository.save(user5DTO.toEntity());

        // 수정한 사용자 조회/반환
        Optional<User5> result = user5Repository.findById(user5DTO.getSeq());
        return result.get().toDTO();


    };

    public ResponseEntity deleteUser5(String seq){

        // 삭제 전 삭제할 사용자 조회
        Optional<User5> optUser5 = user5Repository.findById(seq);

        if(optUser5.isPresent()){
            // 사용자가 존재하면 삭제 후 삭제한 사용자 정보 ResponseEntity로 반환
            user5Repository.deleteById(seq);
            return ResponseEntity
                    .ok()
                    .body(optUser5.get());
        }else{
            // 사용자가 존재하지 않으면 NOT_FOUND 응답데이터와 user not found 메세지
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("user not found");
        }
    }
}