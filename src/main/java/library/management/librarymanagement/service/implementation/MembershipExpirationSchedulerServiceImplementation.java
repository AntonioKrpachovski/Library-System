package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.Member;
import library.management.librarymanagement.model.enums.MembershipStatus;
import library.management.librarymanagement.repository.MemberRepository;
import library.management.librarymanagement.service.MembershipExpirationSchedulerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MembershipExpirationSchedulerServiceImplementation implements MembershipExpirationSchedulerService {

    private final MemberRepository memberRepository;

    @Scheduled(fixedRate = 20000)
    public void expirePastDueMemberships() {
        LocalDateTime now = LocalDateTime.now();

        List<Member> expiredMembers = memberRepository
                .findByExpirationDateBeforeAndStatusNot(now, MembershipStatus.EXPIRED);

        if (expiredMembers.isEmpty()) {
            return;
        }
        for (Member member : expiredMembers) {
            member.setStatus(MembershipStatus.EXPIRED);
            memberRepository.save(member);
        }
    }
}