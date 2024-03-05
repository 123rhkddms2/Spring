package kr.co.ch04.dao;

import kr.co.ch04.dto.User3DTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User3RowMapper implements RowMapper<User3DTO> {

    /*
         - SELECT 결과 처리 메서드
         - SELECT 결과가 1개 이상이면 리스트로 생성
         - SELECT 결과가 1개이면 DTO 생성
     */

    @Override
    public User3DTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        User3DTO user3DTO = new User3DTO();
        user3DTO.setUid(rs.getString(1));
        user3DTO.setName(rs.getString(2));
        user3DTO.setBirth(rs.getString(3));
        user3DTO.setHp(rs.getString(4));
        user3DTO.setAddr(rs.getString(5));

        return user3DTO;

    }
}
