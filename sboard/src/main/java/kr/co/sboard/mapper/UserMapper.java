package kr.co.sboard.mapper;

import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public TermsDTO selectTerms();
    public void insertUser(UserDTO userDTO);
    public int selectCountUser(@Param("type") String type, @Param("value") String value);

    public String checkUserForFindId(@Param("email") String email);
    public UserDTO selectUserForFindId(String name, String email);

    public String checkUserForFindPassword(@Param("email") String email);
    public UserDTO selectUserForFindPassword(String uid, String email);

    public void updateUserForPasswordChange(UserDTO userDTO);

    public void selectUsers();
    public void updateUser();
    public void deleteUser();

}