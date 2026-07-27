package library.management.librarymanagement.service.implementation;

import library.management.librarymanagement.model.MembershipNumberGenerator;
import library.management.librarymanagement.repository.MembershipNumberGeneratorRepository;
import library.management.librarymanagement.service.MembershipNumberGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MembershipNumberGeneratorServiceImplementation implements MembershipNumberGeneratorService {

    private final MembershipNumberGeneratorRepository membershipNumberGeneratorRepository;

    @Override
    public String generateNumber() {

        int year = LocalDateTime.now().getYear();

        MembershipNumberGenerator number =
                membershipNumberGeneratorRepository.findById(year)
                        .orElse(new MembershipNumberGenerator(year, 0));

        int next = number.getLastNumber() + 1;

        number.setLastNumber(next);

        membershipNumberGeneratorRepository.save(number);

        return String.format("MEM-%d-%05d", year, next);
    }
}
