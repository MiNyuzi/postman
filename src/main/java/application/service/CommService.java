package application.service;

import application.entity.CommEntity;
import application.entity.PostEntity;
import application.repository.CommRepository;
import application.repository.PostRepository;
import application.web.dto.CommDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommService {
    private final CommRepository commRepository;
    private final PostRepository postRepository;

    // Dto를 Entity로 변환
    public Integer save(CommDto commDto) {
        //부모엔티티(PostEntity) 조회
        Optional<PostEntity> optionalPost = postRepository.findById(commDto.getPost_id());
        //부모엔티티 있을 때 댓글 저장
        if (optionalPost.isPresent()) {
            PostEntity post = optionalPost.get();
            CommEntity comment = CommEntity.toSaveEntity(commDto, post);
            return commRepository.save(comment).getId();
        } else {
            return null;
        }
    }

    public List<CommDto> findAll(Integer post_id) {
        //select * from comments_table where post_id=? order by id dasc;
        //게시글 기준으로 목록을 가져오고 아이디 기준으로 내림차순
        PostEntity post = postRepository.findById(post_id).get();
        List<CommEntity> commentList = commRepository.findAllByPostOOrderByIdDesc(post);
        //Entity 리스트를 Dto 리스트로 바꿔주기
        List<CommDto> commDtoList = new ArrayList<>();
        for (CommEntity comment: commentList) {
            CommDto commDto = CommDto.toCommDto(comment, post_id);
            commDtoList.add(commDto);
        }
        return commDtoList;
    }
}
