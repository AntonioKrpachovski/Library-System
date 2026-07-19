package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.exceptions.InvalidArgumentsException;
import library.management.librarymanagement.model.exceptions.InvalidUserCredentialsException;
import library.management.librarymanagement.repository.UserRepository;
import library.management.librarymanagement.service.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(InvalidUserCredentialsException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidUserCredentialsException();
        }

        if (!user.isEnabled()) {
            throw new InvalidUserCredentialsException();
        }

        return user;
    }
}
