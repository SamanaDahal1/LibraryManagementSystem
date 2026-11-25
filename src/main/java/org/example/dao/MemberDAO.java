package org.example.dao;

import org.example.model.Member;

import java.util.List;

public interface MemberDAO {
    boolean addMember(Member member);
    Member getMemberById(int memberId);
    List<Member> getAllMembers();
    boolean updateMember(Member member);
    boolean deleteMember(int memberId);
    Member getMemberByEmail(String email);
}
