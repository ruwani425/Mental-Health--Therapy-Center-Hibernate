package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientTM {
    private String patientId;
    private String nic;
    private String name;
    private String address;
    private String gender;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
}
