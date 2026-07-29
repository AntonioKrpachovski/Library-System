package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.BookCopy;
import library.management.librarymanagement.model.Member;
import library.management.librarymanagement.model.MembershipNumberGenerator;
import library.management.librarymanagement.model.dtos.MemberDTO;
import library.management.librarymanagement.model.dtos.MemberEditDTO;
import library.management.librarymanagement.model.enums.MembershipStatus;
import library.management.librarymanagement.repository.MemberRepository;
import library.management.librarymanagement.service.MemberService;
import library.management.librarymanagement.service.MembershipNumberGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImplementation implements MemberService {

    private final MemberRepository memberRepository;
    private final MembershipNumberGeneratorService membershipNumberGeneratorService;


    public Member addMember(MemberDTO memberInfo) {

        Member member = new Member(memberInfo);
        member.setMembershipNumber(
                membershipNumberGeneratorService.generateNumber()
        );

        return memberRepository.save(member);
    }

    public Member suspendMember(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow();
        member.setStatus(MembershipStatus.SUSPENDED);

        return memberRepository.save(member);
    }

    @Override
    public Member editMember(Long memberId, MemberEditDTO memberInfo) {

        Member member = memberRepository.findById(memberId).orElseThrow();

        member.setPhoneNumber(memberInfo.getPhoneNumber());
        member.setAddress(memberInfo.getAddress());
        member.setExpirationDate(memberInfo.getExpirationDate());
        member.setMaxLoans(memberInfo.getMaxLoans());
        member.setStatus(memberInfo.getStatus());

        return memberRepository.save(member);
    }

    @Override
    public Member reactivateMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.setStatus(MembershipStatus.ACTIVE);

        return memberRepository.save(member);
    }

    @Override
    public Member renewMember(Long memberId) {
        return null;
    }

    @Override
    public List<BookCopy> viewLoans(Long memberId) {
        return null;
    }

    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member deactivateMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.setStatus(MembershipStatus.EXPIRED);

        return memberRepository.save(member);
    }
}
