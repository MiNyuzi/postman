package com.postman1.postman1.web.controller;

import com.postman1.postman1.service.CommService;
import com.postman1.postman1.web.dto.CommDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommController {

    private final CommService commService;

    public CommController(CommService commService) {
        this.commService = commService;
    }

    @PostMapping("/save")
    public ResponseEntity sava(@ModelAttribute CommDto commDto) {
        System.out.println("commentDto = "+commDto);
        Integer saveResult = commService.save(commDto);
        if (saveResult != null) {
            //작성 성공하면 댓글목록을 가져와서 리턴
            //댓글목록: 해당 게시글의 댓글 전체
            //해당 게시글 아이디 기준으로 그 아이디에 해당하는 댓글 전체를 가져오기
            List<CommDto> commDtoList = commService.findAll(commDto.getPost_id());
            return new ResponseEntity<>(commDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
