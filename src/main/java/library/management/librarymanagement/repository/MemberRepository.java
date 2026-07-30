package library.management.librarymanagement.repository;

import library.management.librarymanagement.model.Book;
import library.management.librarymanagement.model.Category;
import library.management.librarymanagement.model.Member;
import library.management.librarymanagement.model.enums.MembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);
    List<Member> findByExpirationDateBeforeAndStatusNot(LocalDateTime dateTime, MembershipStatus status);
}
