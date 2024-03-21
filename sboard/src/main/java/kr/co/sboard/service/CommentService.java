package kr.co.sboard.service;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<Article> insertComment(ArticleDTO articleDTO) {
        Article article = modelMapper.map(articleDTO, Article.class);           // articleDTO를 Article엔티티로 변환
        Article savedArticle = articleRepository.save(article);                 // Article객체(savedArticle)을 ArticleRepository의 save메서드를 사용해 DB에 저장

        return ResponseEntity.ok().body(savedArticle);                          // 저장된 Article객체(savedArticle)를 ResponseEntity에 담아서 반환
    }

    public ResponseEntity<List<ArticleDTO>> selectComments(int no) {            

        // ArticleRepository > findByParent() 쿼리 메서드 정의
        List<Article> articleList = articleRepository.findByParent(no);         // articleRepository.findByParent(no)를 호출해 `no`에 해당하는 글들을 DB에서 조회

        List<ArticleDTO> articleDTOS = articleList.stream()                     // 조회된 댓글(articleList)에 대해 스트림을 생성
                .map(entity -> modelMapper.map(entity, ArticleDTO.class))       // Article 엔티티를 ArticleDTO로 매핑
                .toList();                                                      // 변환된 ArticleDTO 객체들을 리스트로 수집

        return ResponseEntity.ok().body(articleDTOS);                           // 변환된 List<ArticleDTO>를 ResponseEntity에 담아서 반환
    }

    public ResponseEntity<?> deleteComment(int no) {

        log.info("no : " + no);
        // 삭제 전 조회
        Optional<Article> optArticle = articleRepository.findById(no);          // articleRepository.findById(no)를 호출해 `no`에 해당하는 글들을 DB에서 조회
                                                                                // Optional을 쓰는 이유는 댓글이 없을 수도 있기때문
        log.info("optArticle : " + optArticle);

        if(optArticle.isPresent()){                                             // 조건문으로 Optional에 댓글이 있는지 확인

            log.info("here1");

            articleRepository.deleteById(no);                                   // 있는경우 no에 해당하는 댓글 삭제

            return ResponseEntity                                               // 삭제된 댓글에 대한 ResponEntity를 반환
                    .ok()
                    .body(optArticle.get());
        }else{                                                                  

            log.info("here2");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)                               // 없는 경우 NOT_FOUND상태와 함께 메시지를 포함한 ResponseEntity를 반환
                    .body("comment not found");

        }
    }

    public ResponseEntity<?> updateComment(ArticleDTO articleDTO){

        // 수정하기 전에 먼저 존재여부 확인(기존의 selectComment와 update기능을 다 담당하려는거같음)
        Optional<Article> optArticle = articleRepository.findById(articleDTO.getNo());

        if(optArticle.isPresent()) {

            Article article = optArticle.get();                                 // optArticle에서 실제 Article 객체를 가져옴
            // 어쩔 수 없이 Article 엔티티에 @Setter 선언해서 수정하기
            article.setContent(articleDTO.getContent());                        // 가져온 Article 객체의 내용을 업데이트

            log.info("article : " + article);


            Article modifiedArticle = articleRepository.save(article);          // save로 해당 엔티티를 DB에 업데이트

            // 수정 후 수정 데이터 반환
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(modifiedArticle);
        }else{
            // 사용자가 존재하지 않으면 NOT_FOUND 응답데이터와 user not found 메세지
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("not found");
        }

    }


}