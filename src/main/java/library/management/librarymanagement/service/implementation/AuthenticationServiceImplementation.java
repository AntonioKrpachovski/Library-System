package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.exceptions.InvalidArgumentsException;
import library.management.librarymanagement.model.exceptions.InvalidUserCredentialsException;
import library.management.librarymanagement.repository.UserRepository;
import library.management.librarymanagement.service.AuthenticationService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class AuthenticationServiceImplementation implements AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        return this.userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}