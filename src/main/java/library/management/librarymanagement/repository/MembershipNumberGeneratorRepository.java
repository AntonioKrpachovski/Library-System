package library.management.librarymanagement.repository;

import library.management.librarymanagement.model.MembershipNumberGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipNumberGeneratorRepository extends JpaRepository<MembershipNumberGenerator, Integer> {
}