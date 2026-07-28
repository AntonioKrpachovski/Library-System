package library.management.librarymanagement.model.dtos;

import library.management.librarymanagement.model.enums.MembershipStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDTO {
    private long phoneNumber;
    private String address;
    private LocalDateTime expirationDate;
    private int maxLoans;
}