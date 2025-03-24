package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Therapist {
    private String therapistId;
    private String therapistName;
    private String email;
    private String phone;
    private String address;
    private String dateOfBirth;
    private String status;
}
