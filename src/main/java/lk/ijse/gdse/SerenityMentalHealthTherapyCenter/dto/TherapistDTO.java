package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TherapistDTO {
    private int therapistId;
    private String therapistName;
    private String email;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private String status;
    private int programID;

    public TherapistDTO(String name, String email, String phone, String address, Date dob, String status, int programID) {
        this.therapistName = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dob;
        this.status = status;
        this.programID = programID;
    }
}
