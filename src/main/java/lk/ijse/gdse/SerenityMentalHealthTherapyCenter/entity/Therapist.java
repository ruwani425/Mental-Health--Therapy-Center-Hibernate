package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Therapist {
    @Id
    private String therapistId;
    private String therapistName;
    private String email;
    private String phone;
    private String address;
    private String dateOfBirth;
    private String status;
}
