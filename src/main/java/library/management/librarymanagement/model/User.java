package library.management.librarymanagement.model;

import jakarta.persistence.*;
import library.management.librarymanagement.model.dtos.RegistrationDTO;
import library.management.librarymanagement.model.dtos.UserDTO;
import library.management.librarymanagement.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "app_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Member member;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private UserRole role;
    @Column(name = "active_status")
    private Boolean activeStatus;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired = true;
    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked = true;
    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired = true;

    public User(String username, String password, String firstName, String lastName, String email, UserRole role, Boolean activeStatus, LocalDateTime creationDate) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.activeStatus = activeStatus;
    }

    public User(String username, String password, String firstName, String lastName, String email, UserRole role, Boolean activeStatus, LocalDateTime creationDate, Member member) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.activeStatus = activeStatus;
        this.member = member;
    }

    public User(UserDTO userInfo) {
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        this.firstName = userInfo.getFirstName();
        this.lastName = userInfo.getLastName();
        this.email = userInfo.getEmail();
        this.role = userInfo.getRole() != null ? userInfo.getRole() : UserRole.MEMBER;
        this.activeStatus = userInfo.getActiveStatus();
    }

    public User(RegistrationDTO registration) {
        this.username = registration.getUsername();
        this.password = registration.getPassword();
        this.firstName = registration.getFirstName();
        this.lastName = registration.getLastName();
        this.email = registration.getEmail();
        this.role = UserRole.MEMBER;
        this.activeStatus = registration.getActiveStatus() != null
                ? registration.getActiveStatus()
                : true;
    }

    @PrePersist
    public void onCreate() {
        creationDate = LocalDateTime.now();
        updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updateDate = LocalDateTime.now();
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
