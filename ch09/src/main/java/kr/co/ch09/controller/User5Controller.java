package kr.co.ch09.controller;

import kr.co.ch09.dto.User5DTO;
import kr.co.ch09.service.User5Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class User5Controller {
    private final User5Service user5Service;

    @GetMapping("/user5")
    public List<User5DTO> list(){
        List<User5DTO> user5s = user5Service.selsectUser5s();

        return user5s;
    }

    @GetMapping("/user5/{seq}")
    public User5DTO user5(@PathVariable("seq") String seq){
        User5DTO user5DTO = user5Service.selectUser5(seq);
        return user5DTO;
    }

    @PostMapping("/user5")
    public ResponseEntity<User5DTO> register(@RequestBody User5DTO user5DTO){
        user5Service.insertUser5(user5DTO);

        return ResponseEntity.ok().body(user5DTO);
    }

    @PutMapping("/user5")
    public ResponseEntity<User5DTO> modify(@RequestBody User5DTO user5DTO){
        User5DTO user5 = user5Service.updateUser5(user5DTO);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(user5);
    }

    @DeleteMapping("/user5/{seq}")
    public void delete(@PathVariable("seq") String seq){
        user5Service.deleteUser5(seq);
    }

}
