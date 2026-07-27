package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.dtos.UserDTO;
import library.management.librarymanagement.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
    private Boolean activeStatus;
    private LocalDateTime creationDate;


    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;


    public User(String username, String password, String first_name, String last_name, String email, UserRole role, Boolean active_status, LocalDateTime creation_date) {
        this.username = username;
        this.password = password;
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.role = role;
        this.activeStatus = active_status;
        this.creationDate = creation_date;
    }

    public User(UserDTO userInfo) {
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        this.firstName = userInfo.getFirstName();
        this.lastName = userInfo.getLastName();
        this.email = userInfo.getEmail();
        this.role = userInfo.getRole();
        this.activeStatus = userInfo.getActiveStatus();
        this.creationDate = userInfo.getCreationDate();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(activeStatus);
    }
}
