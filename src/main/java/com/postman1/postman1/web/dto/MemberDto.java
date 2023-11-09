package com.postman1.postman1.web.dto;

import com.postman1.postman1.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor //기본 생성자 만들어 주는 기능
@AllArgsConstructor // 필드 매개변수 생성자 만들어 주는 기능
@ToString
//회원 내용의 정보를 필드로 저장
public class MemberDto {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public static MemberDto toMemberDto(MemberEntity memberEntity){
        MemberDto memberDto = new MemberDto();
        memberEntity.setId(memberEntity.getId());
        memberEntity.setMemberEmail(memberEntity.getMemberEmail());
        memberEntity.setMemberPassword(memberEntity.getMemberPassword());
        memberEntity.setMemberName(memberEntity.getMemberName());
        //dto에 담긴걸 엔티티 개체로 넘기는 작업
        return memberDto;
    }
}
