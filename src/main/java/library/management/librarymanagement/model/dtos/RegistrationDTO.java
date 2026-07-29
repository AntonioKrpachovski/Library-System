package library.management.librarymanagement.model.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean activeStatus;

    private long phoneNumber;
    private String address;
    private LocalDateTime expirationDate;
    private int maxLoans;
}
