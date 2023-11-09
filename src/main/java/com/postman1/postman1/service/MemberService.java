package com.postman1.postman1.service;

import com.example.postman.entity.MemberEntity;
import com.example.postman.repository.MemberRepository;
import com.example.postman.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDto memberDto) {
        // 1.dto->entity 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        //2. repository의 save 메서드 호출
        memberRepository.save(memberEntity);
        // repository의 save 메서드 호출 (조건. entity객체를 넘겨줘야함)

    }

    public MemberDto login(MemberDto memberDto) {
        /*
        1. 회원이 입력한 이메일로 db에서 조회
        2. db에서 조회한 비밀번호와 사용자가 입력한 비밀번호와 일치하는지 판단
         */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDto.getMemberEmail());
        if(byMemberEmail.isPresent()){
            //조회 결과 유(해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberEmail.get();
            //비밀번호 일치
            if(memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())){
            //entity -> dto 변환 후 리턴 이유 다시 들어야함
                MemberDto dto = MemberDto.toMemberDto(memberEntity);
                return dto;
            }else {
                //비밀번호 불일치
                return null;
            }
        } else{
            //조회 결과 무
            return null;
        }
    }

    public List<MemberDto> findAll() {

        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        //엔티티 하나씩 꺼내서 dto에 넣는 과정 필요 for each
        for (MemberEntity memberEntity: memberEntityList){
            memberDtoList.add(MemberDto.toMemberDto(memberEntity));
//            MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
//            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    public MemberDto findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()){
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public MemberDto updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if(optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        }else {
            return null;
        }
    }

    public void update(MemberDto memberDto) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}


