package library.management.librarymanagement.service;

import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, String email,UserRole role);
}