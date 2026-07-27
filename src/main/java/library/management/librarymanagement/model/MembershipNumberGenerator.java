package library.management.librarymanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import library.management.librarymanagement.model.enums.MembershipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class MembershipNumberGenerator {
    @Id
    private Integer year;
    private Integer lastNumber;
}
