package com.postman1.postman1.entity;

import com.postman1.postman1.web.dto.CommDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comments_table")
public class CommEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String author;
    private Integer post_id;

    public CommEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public PostEntity getPostEntity() {
        return postEntity;
    }

    // Post와 Comment의 관계는 1:N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    public static CommEntity toSaveEntity(CommDto commDto, PostEntity post){
        CommEntity comment = new CommEntity();
        comment.setAuthor(commDto.getAuthor());
        comment.setContent(commDto.getContent());
        comment.setPostEntity(post);
        return comment;
    }
}
