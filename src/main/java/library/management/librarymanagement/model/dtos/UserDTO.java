package library.management.librarymanagement.model.dtos;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import library.management.librarymanagement.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    public interface OnCreate {}

    @NotBlank
    private String username;
    @NotBlank(groups = OnCreate.class)
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    private Boolean activeStatus;
    @NotNull(groups = OnCreate.class)
    private UserRole role;
    private MemberEditDTO member;
}
