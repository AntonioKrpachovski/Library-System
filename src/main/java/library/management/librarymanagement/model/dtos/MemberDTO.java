package library.management.librarymanagement.model.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDTO {
    private String phoneNumber;
    private String address;
    @NotNull
    private LocalDateTime expirationDate;
    private int maxLoans;
}
