package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentTM {
    private int appointmentId;
    private Date date;
    private Double balance;
    private String status;
    private String patientId;
    private String therapistId;
    private String programId;
}
