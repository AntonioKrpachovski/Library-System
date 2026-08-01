package library.management.librarymanagement.model.dtos;

import jakarta.validation.constraints.NotNull;
import library.management.librarymanagement.model.enums.MembershipStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberEditDTO {
    private String phoneNumber;
    private String address;
    @NotNull
    private LocalDateTime expirationDate;
    private int maxLoans;
    @NotNull
    private MembershipStatus status;
}
