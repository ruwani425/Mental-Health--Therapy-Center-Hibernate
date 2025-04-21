package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private int appointmentId;
    private Date date;
    private Double balance;
    private String status;
    private String patientId;
    private String therapistId;
    private String programId;

    public AppointmentDTO(Date date, double balance, String status, String patientId, String therapistId, String programId) {
        this.date = date;
        this.balance = balance;
        this.status = status;
        this.patientId = patientId;
        this.therapistId = therapistId;
        this.programId = programId;
    }
}
