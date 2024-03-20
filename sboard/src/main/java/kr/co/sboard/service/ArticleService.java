
package kr.co.sboard.service;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    // RootConfig Bean 생성/등록 (RootConfig에 만들어져있음)
    private final ModelMapper modelMapper;


    public void insertArticle(ArticleDTO articleDTO){

        fileUpload(articleDTO);

        // articleDTO를 articleEntity로 변환
        Article article = modelMapper.map(articleDTO, Article.class);
        log.info(article.toString());


        // 저장
        articleRepository.save(article);
    }

    @Value("${file.upload.path}")
    private String fileUploadPath;

    public void fileUpload(ArticleDTO articleDTO){

        String path = new File(fileUploadPath).getAbsolutePath();

        for(MultipartFile mf : articleDTO.getFiles()){

            String oName = mf.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String sName = UUID.randomUUID().toString() + ext;

            log.info("oName : " + oName);

            try {
                mf.transferTo(new File(path, sName));

            } catch (IOException e) {
                log.error("fileUpload : " + e.getMessage());
            }

        }

    }

}
