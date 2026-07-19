package library.management.librarymanagement.config;

import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.enums.UserRole;
import library.management.librarymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "Admin123!";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername(DEFAULT_ADMIN_USERNAME).isPresent()) {
            return;
        }

        User admin = new User(
                DEFAULT_ADMIN_USERNAME,
                passwordEncoder.encode(DEFAULT_ADMIN_PASSWORD),
                "System",
                "Administrator",
                "admin@library.local",
                UserRole.Administrator,
                true,
                LocalDate.now()
        );

        userRepository.save(admin);
    }
}
