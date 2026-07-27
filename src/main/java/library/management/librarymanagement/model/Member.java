package library.management.librarymanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import library.management.librarymanagement.model.dtos.MemberDTO;
import library.management.librarymanagement.model.enums.MembershipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String membershipNumber;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String address;
    private LocalDateTime registrationDate;
    private LocalDateTime expirationDate;
    private MembershipStatus status;
    private int maxLoans;
    private List<Loan> loans;

    public Member(String firstName, String lastName, String email, long phoneNumber, String address, LocalDateTime expirationDate, int maxLoans) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = LocalDateTime.now();
        this.expirationDate = expirationDate;
        this.maxLoans = maxLoans;
        this.status = MembershipStatus.ACTIVE;
    }

    public Member(MemberDTO memberInfo) {
        this.firstName = memberInfo.getFirstName();
        this.lastName = memberInfo.getLastName();
        this.email = memberInfo.getEmail();
        this.phoneNumber = memberInfo.getPhoneNumber();
        this.address = memberInfo.getAddress();
        this.registrationDate = LocalDateTime.now();
        this.expirationDate = memberInfo.getExpirationDate();
        this.maxLoans = memberInfo.getMaxLoans();
        this.status = MembershipStatus.ACTIVE;
    }
}
