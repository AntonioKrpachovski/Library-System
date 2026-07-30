package library.management.librarymanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import library.management.librarymanagement.model.dtos.MemberDTO;
import library.management.librarymanagement.model.dtos.RegistrationDTO;
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
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(unique = true)
    private String membershipNumber;
    private String phoneNumber;
    private String address;
    private LocalDateTime registrationDate;
    private LocalDateTime expirationDate;
    private MembershipStatus status;
    private int maxLoans;
    @OneToMany(mappedBy = "member")
    private List<Loan> loans;

    public Member(String phoneNumber, String address, LocalDateTime expirationDate, int maxLoans) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = LocalDateTime.now();
        this.expirationDate = expirationDate;
        this.maxLoans = maxLoans;
        this.status = MembershipStatus.ACTIVE;
    }

    public Member(MemberDTO memberInfo) {
        this.phoneNumber = memberInfo.getPhoneNumber();
        this.address = memberInfo.getAddress();
        this.registrationDate = LocalDateTime.now();
        this.expirationDate = memberInfo.getExpirationDate();
        this.maxLoans = memberInfo.getMaxLoans();
        this.status = MembershipStatus.ACTIVE;
    }

    public Member(RegistrationDTO registration) {
        this.phoneNumber = registration.getPhoneNumber();
        this.address = registration.getAddress();
        this.registrationDate = LocalDateTime.now();
        this.expirationDate = registration.getExpirationDate();
        this.maxLoans = registration.getMaxLoans();
        this.status = MembershipStatus.ACTIVE;
    }
}
