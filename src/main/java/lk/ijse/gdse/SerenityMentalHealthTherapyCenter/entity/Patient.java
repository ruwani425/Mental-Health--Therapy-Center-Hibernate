package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Patient {
    @Id
    private String patientId;

    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private String therapyProgram;
}
