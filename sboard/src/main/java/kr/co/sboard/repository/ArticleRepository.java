package kr.co.sboard.repository;

import kr.co.sboard.entity.Article;
import kr.co.sboard.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /*
         - JPA 페이지네이션 처리를 위한 Page 타입으로 반환
         - Page 타입은 한 페이지에 포함된 엔티티 목록을 표현
     */

    // 게시글을 parent, cate로 검색하고, 페이지네이션 기능으로 페이지 단위로 반환을 함
    public Page<Article> findByParentAndCate(int parent, String cate, Pageable pageable);


}