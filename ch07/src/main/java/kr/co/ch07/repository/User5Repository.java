package kr.co.ch07.repository;

import kr.co.ch07.entity.board.User5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User5Repository extends JpaRepository<User5, String> {

    // 사용자 정의 쿼리메서드
    public User5 findUser5ByUid(String seq);
    public List<User5> findUser5ByName(String name);
    public List<User5> findUser5ByNameNot(String name);

    public User5 findUser5ByUidAndName(String seq, String name);
    public List<User5> findUser5ByUidOrName(String seq, String name);

    public List<User5> findUser5ByAgeGreaterThan(int age);
    public List<User5> findUser5ByAgeGreaterThanEqual(int age);
    public List<User5> findUser5ByAgeLessThan(int age);
    public List<User5> findUser5ByAgeLessThanEqual(int age);
    public List<User5> findUser5ByAgeBetween(int low, int high);

    public List<User5> findUser5ByNameLike(String name);
    public List<User5> findUser5ByNameContains(String name);
    public List<User5> findUser5ByNameStartsWith(String name);
    public List<User5> findUser5ByNameEndsWith(String name);

    public List<User5> findUser5ByOrderByName();
    public List<User5> findUser5ByOrderByAgeAsc();
    public List<User5> findUser5ByOrderByAgeDesc();
    public List<User5> findUser5ByAgeGreaterThanOrderByAgeDesc(int age);

    public int countUser5ByUid(String seq);
    public int countUser5ByName(String name);

    // JPQL 정의
    @Query("select u5 from User5 as u5 where u5.age < 30")
    public List<User5> selectUser5UnderAge30();

    @Query("select u5 from User5 as u5 where u5.name = ?5")
    public List<User5> selectUser5ByName(String name);

    @Query("select u5 from User5 as u5 where u5.name = :name")
    public List<User5> selectUser5ByNameParam(@Param("name") String name);

    @Query("select u5.seq, u5.name, u5.age from User5 as u5 where u5.seq = :seq")
    public List<Object[]> selectUser5ByUid(@Param("seq") String seq);
}