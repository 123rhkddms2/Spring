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

    public List<User3> findUser3ByAgeGreaterThan(int age);
    public List<User3> findUser3ByAgeGreaterThanEqual(int age);
    public List<User3> findUser3ByAgeLessThan(int age);
    public List<User3> findUser3ByAgeLessThanEqual(int age);
    public List<User3> findUser3ByAgeBetween(int low, int high);

    public List<User3> findUser3ByNameLike(String name);
    public List<User3> findUser3ByNameContains(String name);
    public List<User3> findUser3ByNameStartsWith(String name);
    public List<User3> findUser3ByNameEndsWith(String name);

    public List<User3> findUser3ByOrderByName();
    public List<User3> findUser3ByOrderByAgeAsc();
    public List<User3> findUser3ByOrderByAgeDesc();
    public List<User3> findUser3ByAgeGreaterThanOrderByAgeDesc(int age);

    public int countUser3ByUid(String uid);
    public int countUser3ByName(String name);

    // JPQL 정의
    @Query("select u3 from User3 as u3 where u3.age < 30")
    public List<User3> selectUser3UnderAge30();

    @Query("select u3 from User3 as u3 where u3.name = ?3")
    public List<User3> selectUser3ByName(String name);

    @Query("select u3 from User3 as u3 where u3.name = :name")
    public List<User3> selectUser3ByNameParam(@Param("name") String name);

    @Query("select u3.uid, u3.name, u3.age from User3 as u3 where u3.uid = :uid")
    public List<Object[]> selectUser3ByUid(@Param("uid") String uid);
}