package kr.co.sboard.service;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.entity.File;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FileService fileService;
    private final FileRepository fileRepository;

    // RootConfig Bean 생성/등록
    private final ModelMapper modelMapper;

    public PageResponseDTO findByParentAndCate(PageRequestDTO pageRequestDTO) {          // ArticleController - list로 감

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Article> pageArticle = articleRepository.findByParentAndCate(0, pageRequestDTO.getCate(), pageable);


        List<ArticleDTO> dtoList = pageArticle.getContent().stream()
                .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                .toList();

        int total = (int) pageArticle.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

    public ArticleDTO findById(int no) {                                 // ArticleController - view로 감

        Optional<Article> optArticle = articleRepository.findById(no);
        log.info("findById...1");

        ArticleDTO articleDTO = null;

        if (optArticle.isPresent()) {
            log.info("findById...2");
            Article article = optArticle.get();

            log.info("findById...3 : " + article.toString());
            articleDTO = modelMapper.map(article, ArticleDTO.class);
            log.info("findById...4");
        }

        log.info("articleDTO : " + articleDTO.toString());

        return articleDTO;
    }

    public ResponseEntity<?> selectArticle(int no) {
        try {
            Article article = articleRepository.findById(no).orElseThrow();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(article);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("article not found");
        }
    }


    public void insertArticle(ArticleDTO articleDTO) {

        // 파일 첨부 처리
        List<FileDTO> files = fileService.fileUpload(articleDTO);

        // 파일 첨부 갯수 초기화
        articleDTO.setFile(files.size());

        // articleDTO를 articleEntity로 변환
        Article article = modelMapper.map(articleDTO, Article.class);
        log.info(article.toString());

        // 저장 후 저장한 엔티티 객체 반환(사실 JPA sava() 메서드는 default로 저장한 Entity를 반환)
        Article savedArticle = articleRepository.save(article);
        log.info("insertArticle : " + savedArticle.toString());

        // 파일 insert
        for (FileDTO fileDTO : files) {

            fileDTO.setAno(savedArticle.getNo());

            // 여기서 에러나는데 RootConfig 파일에 ModelMapper 설정에 이거 추가 -> .setMatchingStrategy(MatchingStrategies.STRICT)
            File file = modelMapper.map(fileDTO, File.class);

            fileRepository.save(file);
        }
    }

    @Transactional
    public void updateArticle(ArticleDTO articleDTO) throws NotFoundException {
        try {
            Article articleToUpdate = articleRepository.findById(articleDTO.getNo())
                    .orElseThrow(() -> new NotFoundException("Article not found: " + articleDTO.getNo()));

            articleToUpdate.setTitle(articleDTO.getTitle());
            articleToUpdate.setContent(articleDTO.getContent());

            articleRepository.save(articleToUpdate);
        } catch (NotFoundException e) {
            // 발생한 예외를 로그에 출력합니다.
            log.error("Article not found: " + articleDTO.getNo(), e);
            // 예외를 다시 던져서 컨트롤러에서 처리할 수 있도록 합니다.
            throw e;
        }
    }
}