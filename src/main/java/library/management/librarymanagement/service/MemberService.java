package library.management.librarymanagement.service;

import library.management.librarymanagement.model.BookCopy;
import library.management.librarymanagement.model.Member;
import library.management.librarymanagement.model.dtos.MemberDTO;
import library.management.librarymanagement.model.dtos.MemberEditDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    Member addMember(MemberDTO memberInfo);
    Member suspendMember(Long memberId);
    Member editMember(Long memberId, MemberEditDTO memberInfo);
    Member reactivateMember(Long memberId);
    Member renewMember(Long memberId);
    List<BookCopy> viewLoans(Long memberId);
}
