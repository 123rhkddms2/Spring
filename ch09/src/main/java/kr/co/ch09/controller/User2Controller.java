package kr.co.ch09.controller;

import kr.co.ch09.dto.User2DTO;
import kr.co.ch09.service.User2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class User2Controller {
    private final User2Service user2Service;

    @GetMapping("/user2")
    public List<User2DTO> list(){
        List<User2DTO> user2s = user2Service.selsectUser2s();

        return user2s;
    }

    @GetMapping("/user2/{uid}")
    public User2DTO user2(@PathVariable("uid") String uid){
        User2DTO user2DTO = user2Service.selectUser2(uid);
        return user2DTO;
    }

    @PostMapping("/user2")
    public ResponseEntity<User2DTO> register(@RequestBody User2DTO user2DTO){
        user2Service.insertUser2(user2DTO);

        return ResponseEntity.ok().body(user2DTO);
    }

    @PutMapping("/user2")
    public ResponseEntity<User2DTO> modify(@RequestBody User2DTO user2DTO){
        User2DTO user2 = user2Service.updateUser2(user2DTO);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(user2);
    }

    @DeleteMapping("/user2/{uid}")
    public void delete(@PathVariable("uid") String uid){
        user2Service.deleteUser2(uid);
    }

}
