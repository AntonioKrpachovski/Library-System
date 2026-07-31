package library.management.librarymanagement.repository;

import library.management.librarymanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    List<User> findByMemberIsNotNull();
    @Query("""
    SELECT u
    FROM User u
    """)
    Page<User> findAllPageable(Pageable pageable);
    @Query("""
    SELECT u
    FROM User u
    WHERE u.member IS NOT NULL
    AND (:searchMembershipNumber IS NULL OR LOWER(u.member.membershipNumber) LIKE LOWER(CONCAT('%',:searchMembershipNumber,'%')))
    AND (:searchFirstName IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchFirstName, '%')))
    AND (:searchLastName IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchLastName, '%')))
    AND (:searchEmail IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :searchEmail, '%')))
    AND (:searchPhoneNumber IS NULL OR LOWER(u.member.phoneNumber) LIKE LOWER(CONCAT('%', :searchPhoneNumber, '%')))
    """)
    Page<User> searchUsers(String searchMembershipNumber, String searchFirstName, String searchLastName, String searchEmail, String searchPhoneNumber, Pageable pageable);
}



