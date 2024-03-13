package kr.co.ch07.repository;

import kr.co.ch07.entity.board.User4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User4Repository extends JpaRepository<User4, String> {

    // 사용자 정의 쿼리메서드
    public User4 findUser4ByUid(String uid);
    public List<User4> findUser4ByName(String name);
    public List<User4> findUser4ByNameNot(String name);

    public User4 findUser4ByUidAndName(String uid, String name);
    public List<User4> findUser4ByUidOrName(String uid, String name);

    public List<User4> findUser4ByAgeGreaterThan(int age);
    public List<User4> findUser4ByAgeGreaterThanEqual(int age);
    public List<User4> findUser4ByAgeLessThan(int age);
    public List<User4> findUser4ByAgeLessThanEqual(int age);
    public List<User4> findUser4ByAgeBetween(int low, int high);

    public List<User4> findUser4ByNameLike(String name);
    public List<User4> findUser4ByNameContains(String name);
    public List<User4> findUser4ByNameStartsWith(String name);
    public List<User4> findUser4ByNameEndsWith(String name);

    public List<User4> findUser4ByOrderByName();
    public List<User4> findUser4ByOrderByAgeAsc();
    public List<User4> findUser4ByOrderByAgeDesc();
    public List<User4> findUser4ByAgeGreaterThanOrderByAgeDesc(int age);

    public int countUser4ByUid(String uid);
    public int countUser4ByName(String name);

    // JPQL 정의
    @Query("select u4 from User4 as u4 where u4.age < 30")
    public List<User4> selectUser4UnderAge30();

    @Query("select u4 from User4 as u4 where u4.name = ?4")
    public List<User4> selectUser4ByName(String name);

    @Query("select u4 from User4 as u4 where u4.name = :name")
    public List<User4> selectUser4ByNameParam(@Param("name") String name);

    @Query("select u4.uid, u4.name, u4.age from User4 as u4 where u4.uid = :uid")
    public List<Object[]> selectUser4ByUid(@Param("uid") String uid);
}