package kr.co.ch09.controller;

import kr.co.ch09.dto.User3DTO;
import kr.co.ch09.service.User3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class User3Controller {

    private final User3Service user3Service;

    /*
        @RequestBody
         - 요청 본문 내용에 포함된 데이터를 Java 객체로 변환
         - 클라이언트에서 전송되는 JSON 데이터를 수신
    
        @ResponseBody
         - 메서드의 반환값을 응답객체 내용 본문으로 출력
         - JSON 출력 어노테이션
         
        @PathVaribale
         - 주소 파라미터 값을 수신

        @RestController
         - API 요청을 처리하는 메서드의 결과를 응답객체 본문에 출력시키는 클래스 어노테이션
        
        @ResponseEntity
          - API를 요청한 사용자에게 응답데이터를 구성해서 부가적인 정보 제공하기 위한 클래스
          - 일반적으로 상태코드(header), 본문내용(body) 을 구성해서 제공
     */

    @GetMapping("/user3")
    public List<User3DTO> list(){

        List<User3DTO> user3s = user3Service.selectUser3s();

        return user3s;
    }

    @GetMapping("/user3/{uid}")
    public User3DTO user3(@PathVariable("uid") String uid){
        User3DTO user3DTO = user3Service.selectUser3(uid);
        return user3DTO;
    }

    @PostMapping("/user3")
    public ResponseEntity<User3DTO> register(@RequestBody User3DTO user3DTO){
        
        user3Service.insertUser3(user3DTO);

        return ResponseEntity.ok().body(user3DTO);
    }

    @PutMapping("/user3")
    public ResponseEntity<User3DTO> modify(@RequestBody User3DTO user3DTO){

        User3DTO user3 = user3Service.updateUser3(user3DTO);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(user3);
    }

    @DeleteMapping("/user3/{uid}")
    public void delete(@PathVariable("uid") String uid){
        user3Service.deleteUser3(uid);

    }

}
