package kr.co.ch04.dao;

import kr.co.ch04.dto.User5DTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User5RowMapper implements RowMapper<User5DTO> {

    /*
         - SELECT 결과 처리 메서드
         - SELECT 결과가 1개 이상이면 리스트로 생성
         - SELECT 결과가 1개이면 DTO 생성
     */

    @Override
    public User5DTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        User5DTO user5DTO = new User5DTO();
        user5DTO.setSeq(rs.getInt(1));
        user5DTO.setName(rs.getString(2));
        user5DTO.setGender(rs.getString(3));
        user5DTO.setAge(rs.getInt(4));
        user5DTO.setAddr(rs.getString(5));

        return user5DTO;

    }
}
