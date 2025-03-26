package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TherapistTM {
    private int therapistId;
    private String therapistName;
    private String email;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private String status;
}
