package kr.co.ch06.Mapper;

import kr.co.ch06.dto.User3DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class User3MapperTest {

    @Autowired
    private User3Mapper mapper;


    @DisplayName("user3 등록")
    void insertUser3() {
        log.info("insertUser3...");
        //given
        User3DTO user3DTO = User3DTO.builder()
                .uid("j107")
                .name("강감찬")
                .birth("1900-01-01")
                .hp("010-0000-0001")
                .addr("부산")
                .build();
        //when
        mapper.insertUser3(user3DTO);
        //then
        User3DTO resultUser3 = mapper.selectUser3(user3DTO.getUid());
        Assertions.assertEquals(user3DTO.getUid(), resultUser3.getUid());
    }

    @DisplayName("user3 조회")
    void selectUser3() {
        log.info("selectUser3...");
        //given
        String uid = "j107";
        //when
        User3DTO user3DTO = mapper.selectUser3(uid);
        log.info(user3DTO.toString());
        //then
        assertEquals(uid, user3DTO.getUid());
    }


    @DisplayName("user3 목록")
    void selectUser3s() {
        log.info("selectUser3s...");
        //given
        List<User3DTO> users = null;
        //when
        users = mapper.selectUser3s();
        for(User3DTO user : users){
            log.info(user.toString());
        }
        //then
        assertFalse(users.isEmpty());
    }


    @DisplayName("user3 수정")
    void updateUser3() {
        log.info("updateUser3...");
        //given
        User3DTO user3DTO = User3DTO.builder()
                .uid("j107")
                .name("강검찬")
                .birth("1999-03-07")
                .hp("010-3326-1860")
                .addr("서울")
                .build();
        //when
        mapper.updateUser3(user3DTO);
        //then
        User3DTO resultUser3 = mapper.selectUser3(user3DTO.getUid());
        assertEquals(user3DTO.getUid(), resultUser3.getUid());
    }

    @Test
    @DisplayName("user3 삭제")
    void deleteUser3() {
        log.info("deleteUser3...");
        //given
        String uid="j107";
        //when
        mapper.deleteUser3(uid);
        //then
        User3DTO resultUser3 = mapper.selectUser3(uid);
        assertNull(resultUser3);
    }
}