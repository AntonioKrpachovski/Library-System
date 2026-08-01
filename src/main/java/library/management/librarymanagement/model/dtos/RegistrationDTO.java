package library.management.librarymanagement.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    private Boolean activeStatus;

    private String phoneNumber;
    private String address;
    @NotNull
    private LocalDateTime expirationDate;
    private int maxLoans;
}
