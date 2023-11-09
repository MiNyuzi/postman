package com.postman1.postman1.repository;

import com.postman1.postman1.entity.CommEntity;
import com.postman1.postman1.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommRepository extends JpaRepository<CommEntity, Long> {
    //select * from comments_table where post_id=? order by id dasc;
    //게시글 기준으로 목록을 가져오고 아이디 기준으로 내림차순
    List<CommEntity> findAllByPostOOrderByIdDesc(PostEntity post);
}
