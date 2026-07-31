package library.management.librarymanagement.service;

import library.management.librarymanagement.model.User;
import library.management.librarymanagement.model.dtos.MemberDTO;
import library.management.librarymanagement.model.dtos.RegistrationDTO;
import library.management.librarymanagement.model.dtos.UserDTO;
import library.management.librarymanagement.model.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, String email,UserRole role);
    Page<User> getAllUsersPageable(Pageable pageable);
    User editUser(Long userId, UserDTO userInfo);
    User addUser(UserDTO userInfo);
    User deleteUser(Long userId);
    User findUserById(Long userId);
    User addUserWithMember(RegistrationDTO registrationDTO);
    List<User> getAllMemberUsers();
    Page<User> searchMembers(String searchMembershipNumber, String searchFirstName, String searchLastName, String searchEmail, String searchPhoneNumber, Pageable pageable);
}