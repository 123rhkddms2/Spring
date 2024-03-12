package kr.co.ch06.Mapper;

import kr.co.ch06.dto.User1DTO;
import kr.co.ch06.dto.User5DTO;
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
class User5MapperTest {

    @Autowired
    private User5Mapper mapper;

    @Test
    @DisplayName("user5 등록")
    void insertUser5() {
        log.info("insertUser5...");
        // given
        User5DTO user5DTO = User5DTO.builder()
                            .seq("4")
                            .name("강감찬")
                            .gender("M")
                            .age(22)
                            .addr("울산")
                            .build();

        // when
        mapper.insertUser5(user5DTO);

        //then
        User5DTO resultUser5 = mapper.selectUser5(user5DTO.getSeq_user5());
        Assertions.assertEquals(user5DTO.getSeq_user5(), resultUser5.getSeq_user5());

    }
    
    @DisplayName("user5 조회")
    void selectUser5() {
        log.info("selectUser5...");
        // given
        String seq = "6";

        // when
        User5DTO user5DTO = mapper.selectUser5(seq);
        log.info(user5DTO.toString());

        //then
        assertEquals(seq, user5DTO.getSeq_user5());


    }

    @DisplayName("user5 목록")
    void selectUser5s() {
        log.info("selectUser5s...");
        // given
        List<User5DTO> users = null;

        // when
        users = mapper.selectUser5s();
        for(User5DTO user : users){
            log.info(user.toString());
        }

        //then
        assertFalse(users.isEmpty());
    }

    @DisplayName("user5 수정")
    void updateUser5() {
        log.info("updateUser5...");
        // given
        User5DTO user5DTO = User5DTO.builder()
                .seq("")
                .name("강검찬")
                .gender("M")
                .age(21)
                .addr("광교")
                .build();

        // when
        mapper.updateUser5(user5DTO);

        //then
        User5DTO resultUser5 = mapper.selectUser5(user5DTO.getSeq_user5());
        assertEquals(user5DTO.getName(), resultUser5.getName());

    }

    @DisplayName("user5 삭제")
    void deleteUser5() {
        log.info("deleteUser5...");
        // given
        String seq = "6";

        // when
        mapper.deleteUser5(seq);

        //then
        User5DTO resultUser5 = mapper.selectUser5(seq);
        assertNull(resultUser5);

    }
}