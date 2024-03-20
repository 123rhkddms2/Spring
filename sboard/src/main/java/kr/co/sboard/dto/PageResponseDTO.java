package kr.co.sboard.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageResponseDTO {

    private List<ArticleDTO> dtoList;       // 페이징된 게시글 목록을 담는 리스트
    private String cate;                    // 게시글 카테고리
    private int pg;                         // 현재 페이지 번호
    private int size;                       // 한 페이지에 출력되는 번호
    private int total;                      // 전체 게시글 수
    private int startNo;                    // 현재 페이지의 첫 번째 게시글 번호
    private int start, end;                 // 현재 페이지의 시작 페이지 번호, 끝 페이지 번호
    private boolean prev, next;             // 이전 페이지와 다음 페이지의 존재 여부

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<ArticleDTO> dtoList, int total){
        this.cate = pageRequestDTO.getCate();
        this.pg = pageRequestDTO.getPg();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.startNo = total - ((pg - 1) * size);
        this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int) (Math.ceil(total / (double) size));
        this.end = end > last ? last : end;
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }
}
