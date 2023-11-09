package com.postman1.postman1.web.dto;

import com.postman1.postman1.entity.CommEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommDto {
    private Integer id;
    private String author;
    private String content;
    private Integer post_id;
    private LocalDateTime comm_at;

    
    //엔티티를 디티오로 변환
    public static CommDto toCommDto(CommEntity comment, Integer post_id) {
        CommDto commDto = new CommDto();
        commDto.setId(comment.getId());
        commDto.setAuthor(comment.getAuthor());
        commDto.setContent(comment.getContent());
//        commDto.setPost_id(comment.getPostEntity().getId());
        commDto.setPost_id(post_id);
        return commDto;
    }
}
