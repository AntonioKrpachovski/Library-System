package library.management.librarymanagement.model.dtos;

import jakarta.persistence.Entity;
import library.management.librarymanagement.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private Boolean activeStatus;
    private LocalDateTime creationDate;
}
