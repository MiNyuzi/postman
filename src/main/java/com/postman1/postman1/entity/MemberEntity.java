package com.postman1.postman1.entity;

import com.postman1.postman1.web.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id //pk지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(unique = true) //unique 제약 추가
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberName(memberDto.getMemberName());
        //dto에 담긴걸 엔티티 개체로 넘기는 작업
        return memberEntity;
    }
    public static MemberEntity toUpdateMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId());
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberName(memberDto.getMemberName());
        //dto에 담긴걸 엔티티 개체로 넘기는 작업
        return memberEntity;
    }

}
