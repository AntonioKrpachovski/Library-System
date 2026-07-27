package library.management.librarymanagement.service;

import org.springframework.stereotype.Service;

@Service
public interface MembershipNumberGeneratorService {
    String generateNumber();
}
