package kr.co.ch07.repository;

import kr.co.ch07.entity.board.User3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User3Repository extends JpaRepository<User3, String> {

    // 사용자 정의 쿼리메서드
    public User3 findUser3ByUid(String uid);
    public List<User3> findUser3ByName(String name);
    public List<User3> findUser3ByNameNot(String name);

    public User3 findUser3ByUidAndName(String uid, String name);
    public List<User3> findUser3ByUidOrName(String uid, String name);

    public List<User3> findUser3ByNameLike(String name);
    public List<User3> findUser3ByNameContains(String name);
    public List<User3> findUser3ByNameStartsWith(String name);
    public List<User3> findUser3ByNameEndsWith(String name);

    public int countUser3ByUid(String uid);
    public int countUser3ByName(String name);

    // JPQL 정의


    @Query("select u3 from User3 as u3 where u3.name = ?3")
    public List<User3> selectUser3ByName(String name);

    @Query("select u3 from User3 as u3 where u3.name = :name")
    public List<User3> selectUser3ByNameParam(@Param("name") String name);


}