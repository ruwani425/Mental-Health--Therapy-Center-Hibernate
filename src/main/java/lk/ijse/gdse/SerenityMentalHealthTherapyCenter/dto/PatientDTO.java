package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private String patientId;
    private String name;
    private String address;
    private String gender;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String nic;

    public PatientDTO(String nic, String name, String address, String gender, Date dateOfBirth, String email, String phone) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phone;
    }

    public PatientDTO(String id, String nic, String name, String address, String gender, Date dateOfBirth, String email, String phone) {
        this.patientId = id;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phone;
    }
}
