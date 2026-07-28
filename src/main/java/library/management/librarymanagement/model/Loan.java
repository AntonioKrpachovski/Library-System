package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private BookCopy bookCopy;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private LoanStatus status;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
