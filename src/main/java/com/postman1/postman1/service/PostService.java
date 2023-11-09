package com.postman1.postman1.service;

import com.postman1.postman1.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.postman1.postman1.repository.PostRepository;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PostService {

    //PostRepository를 자동으로 연결
    @Autowired
    private PostRepository postRepository;

    //게시물을 작성하고 파일을 저장하는 기능을 담당
    public void write(Post post, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        post.setFilename(fileName);
        post.setFilepath("/files/" + fileName);
        postRepository.save(post);
    }


    //게시글 리스트 처리
    //게시물의 전체 목록. 게시글 전체를 대상으로 함. 페이지네이션된 게시물 목록을 반환하는 기능을 담당합
    public Page<Post> postList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    //특정 키워드로 게시물 검색. 특정 검색어를 제목에 포함하는 게시글만을 대상
    public Page<Post> postSearchList(String searchKeyword, Pageable pageable) {
        return postRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 불러오기. 특정 게시글의 상세 정보를 보여주는 역할
    public Post postView(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 게시물이 존재하지 않습니다."));
    }

    //작성자 이메일로 게시물 조회 메서드 추가
    public Page<Post> postListByEmail(String email, Pageable pageable) {
        return postRepository.findByEmail(email, pageable);
    }

    //게시물 삭제 메드 추가
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }


}

