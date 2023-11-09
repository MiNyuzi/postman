package com.postman1.postman1.web.controller;

import com.postman1.postman1.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import com.postman1.postman1.service.PostService;

@Controller
public class PostController {


    @Autowired
    private PostService postService;

    //게시글 작성 페이지로 이동."postwrite"라는 이름의 뷰로 이동
    @GetMapping("/post/write") //localhost:8080/post/write
    public String postWriteForm() {
        return "postwrite";
    }

    //게시글 작성
    //postService의 write를 호출하여 게시글을 작성하고, '글 작성이 완료되었습니다' 라는 메시지와 함께 "message"라는 이름의 뷰로 이동
    @PostMapping("/post/writepro")
    public String postWritePro(Post post, Model model, MultipartFile file) throws Exception{
        postService.write(post, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/post/list");
        return "message";
    }

    //게시글 목록 보여주는 페이지
    //페이징 처리와 검색 기능을 포함
    //검색어가 없을 경우 전체 게시글을, 검색어가 있을 경우 검색어에 해당하는 게시글을 가져와서 "postlist"라는 이름의 뷰에 전달합니다.
    @GetMapping("/post/list")
    public String postList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Post> list = null;

        if(searchKeyword == null) {
            list = postService.postList(pageable);
        }else {
            list = postService.postSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "postlist";
    }

    //특정 게시글을 보여주는 메서드
    //게시글의 ID를 받아 해당 게시글을 가져와서 "postview"라는 이름의 뷰에 전달합
    @GetMapping("/post/view") // localhost:8080/post/view?id=1
    public String postView(Model model, Integer id) {
        model.addAttribute("post", postService.postView(id));
        return "postview";
    }

    //게시물 수정 페이지로 이동
    //수정할 게시글의 ID를 받아 해당 게시글을 가져와서 "postmodify"라는 이름의 뷰에 전달
    @GetMapping("/post/modify/{id}")
    public String postModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("post", postService.postView(id));
        return "postmodify";
    }

    //게시글 수정을 처리하는 메서드
    //수정된 게시글 정보와 파일을 받아 PostService의 write 메서드를 호출하여 게시글을 업데이트하고, 게시글 목록 페이지로 다시 보냄
    @PostMapping("/post/update/{id}")
    public String postUpdate(@PathVariable("id") Integer id, Post post, MultipartFile file) throws Exception{
        Post postTemp = postService.postView(id);
        postTemp.setTitle(post.getTitle());
        postTemp.setContent(post.getContent());
        postService.write(postTemp, file);
        return "redirect:/post/list";
    }

    // 작성자 이메일로 게시물 조회 메서드
    @GetMapping("/post/listByEmail/{email}")
    public String postListByEmail(@PathVariable("email") String email, Model model,
                                  @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> list = postService.postListByEmail(email, pageable);
        // 페이지네이션 및 뷰 설정 코드
        return "postlist";
    }

    //게시글 삭제 메서드
    //삭제할 게시글의 ID를 받아 PostService의 deletePost 메소드를 호출하여 게시글을 삭제하고, 게시글 목록 페이지로 다시 돌려보냄
    @PostMapping("/post/delete/{id}")
    public String deletePost(@PathVariable("id") Integer id) {
        postService.deletePost(id);
        return "redirect:/post/list";
    }
}
