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
public class TherapyProgram {
    @Id
    private String programId;
    private String programName;
    private String duration;
    private double programFee;
}
