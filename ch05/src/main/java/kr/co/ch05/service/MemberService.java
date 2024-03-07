package kr.co.ch05.service;

import kr.co.ch05.dto.MemberDTO;
import kr.co.ch05.dto.ParentDTO;
import kr.co.ch05.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public void insertMember(MemberDTO memberDTO){
        memberMapper.insertMember(memberDTO);
    }
    public MemberDTO selectMember(String uid){
        return memberMapper.selectMember(uid);
    }
    public List<MemberDTO> selectMembers(){
        return memberMapper.selectMembers();
    }
    public List<MemberDTO> selectMembersForSearch(String type, String value, String[] pos){
        return memberMapper.selectMembersForSearch(type, value, pos);
    }

    public List<ParentDTO> selectParentWithChild() {
        return memberMapper.selectParentWithChild();
    }
    public void updateMember(MemberDTO memberDTO){
        memberMapper.updateMember(memberDTO);
    }
    public void deleteMember(String uid){
        memberMapper.deleteMember(uid);
    }

}