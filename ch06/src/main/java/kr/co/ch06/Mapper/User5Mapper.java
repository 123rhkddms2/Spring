package kr.co.ch06.Mapper;

import kr.co.ch06.dto.User5DTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface User5Mapper {
    public void insertUser5(User5DTO user5DTO);
    public User5DTO selectUser5(String seq);
    public List<User5DTO> selectUser5s();
    public void updateUser5(User5DTO user5DTO);
    public void deleteUser5(String seq);
}
