package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    private String appointmentId;
    private Date startDate;
    private Date endDate;
    private String description;

    @ManyToOne
    private Therapist therapist;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private TherapyProgram therapyProgram;

    @ManyToOne
    private Payment payment;
}
