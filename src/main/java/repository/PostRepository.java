package repository;

import entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
//PostRepository 인터페이스가 JpaRepository를 상속받음을 나타냄
//이렇게 함으로써 CRUD(Create, Read, Update, Delete) 연산을 수행하는 여러 메서드를 자동으로 사용할 수 있게 됨
//여기서 'Post'는 엔티티 타입, 'Integer'는 기본 키의 타입
public interface PostRepository extends JpaRepository<Post, Integer> {

    //제목에 특정 키워드가 포함된 게시물을 페이지네이션으로 조회하는 기능을 담당
    //Pageable 파라미터를 통해 페이지네이션 정보(페이지 번호, 페이지 크기, 정렬 방식 등)를 전달받음
    Page<Post> findByTitleContaining(String searchKeyword, Pageable pageable); //키워드로 게시물 조회 메서드

    //작성자의 이메일로 게시물을 페이지네이션으로 조회하는 기능을 담당
    Page<Post> findByEmail(String email, Pageable pageable); // 작성자 이메일로 게시물 조회 메서드

    void deleteById(Integer id); // 게시물 삭제 메서드
}

