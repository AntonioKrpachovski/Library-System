package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.dtos.MemberDTO;
import library.management.librarymanagement.model.dtos.RegistrationDTO;
import library.management.librarymanagement.model.enums.MembershipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "membership_number", unique = true)
    private String membershipNumber;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Column(name = "status")
    private MembershipStatus status;
    @Column(name = "max_loans")
    private int maxLoans;
    @OneToMany(mappedBy = "member")
    private List<Loan> loans;

    public Member(String phoneNumber, String address, LocalDateTime expirationDate, int maxLoans) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.expirationDate = expirationDate;
        this.maxLoans = maxLoans;
        this.status = MembershipStatus.ACTIVE;
    }

    public Member(MemberDTO memberInfo) {
        this.phoneNumber = memberInfo.getPhoneNumber();
        this.address = memberInfo.getAddress();
        this.expirationDate = memberInfo.getExpirationDate();
        this.maxLoans = memberInfo.getMaxLoans();
        this.status = MembershipStatus.ACTIVE;
    }

    public Member(RegistrationDTO registration) {
        this.phoneNumber = registration.getPhoneNumber();
        this.address = registration.getAddress();
        this.expirationDate = registration.getExpirationDate();
        this.maxLoans = registration.getMaxLoans();
        this.status = MembershipStatus.ACTIVE;
    }

    @PrePersist
    public void onCreate() {
        registrationDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updateDate = LocalDateTime.now();
    }
}
