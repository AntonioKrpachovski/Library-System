package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.Member;
import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.dtos.MemberDTO;
import library.management.librarymanagement.model.dtos.RegistrationDTO;
import library.management.librarymanagement.model.dtos.UserDTO;
import library.management.librarymanagement.model.enums.UserRole;
import library.management.librarymanagement.model.exceptions.InvalidArgumentsException;
import library.management.librarymanagement.model.exceptions.PasswordsDoNotMatchException;
import library.management.librarymanagement.model.exceptions.UsernameAlreadyExistsException;
import library.management.librarymanagement.repository.UserRepository;
import library.management.librarymanagement.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname,String email, UserRole role) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(username, passwordEncoder.encode(password), name, surname, email ,role, true, LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User editUser(Long userId, UserDTO userInfo) {
        User user = userRepository.findById(userId).orElseThrow();

        user.setUsername(userInfo.getUsername());
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setEmail(userInfo.getEmail());
        user.setActiveStatus(userInfo.getActiveStatus());
        return userRepository.save(user);
    }

    @Override
    public User addUser(UserDTO userInfo) {
        User user = new User(userInfo);

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        userRepository.delete(user);

        return user;
    }

    @Override
    public User addUserWithMember(RegistrationDTO registration) {

        User user = new User(registration);
        user.setPassword(passwordEncoder.encode(registration.getPassword()));

        Member member = new Member(registration);

        user.setMember(member);
        member.setUser(user);

        return userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMINISTRATOR') or #userId == authentication.principal.id")
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public List<User> getAllMemberUsers() {
        return userRepository.findByMemberIsNotNull();
    }
}