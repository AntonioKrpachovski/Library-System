package library.management.librarymanagement.service;

import library.management.librarymanagement.model.User;

public interface AuthenticationService {
    User login(String username, String password);
}
