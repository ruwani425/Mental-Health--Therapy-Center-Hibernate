package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TherapyProgramTM {
    private int programId;
    private String programName;
    private String duration;
    private double programFee;
}
