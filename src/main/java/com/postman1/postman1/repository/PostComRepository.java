package com.postman1.postman1.repository;

import com.postman1.postman1.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostComRepository extends JpaRepository<PostEntity, Integer> {
    Page<PostEntity> findByTitleContaining(String searchKeyword, Pageable pageable);

}
