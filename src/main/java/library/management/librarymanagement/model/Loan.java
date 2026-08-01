package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;
    @Column(name = "loan_date")
    private LocalDateTime loanDate;
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    @Column(name = "status")
    private LoanStatus status;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
