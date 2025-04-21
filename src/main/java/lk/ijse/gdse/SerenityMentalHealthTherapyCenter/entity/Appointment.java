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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appointmentId;
    private Date date;
    private Double balance;
    private String status;

    @ManyToOne
    private Therapist therapist;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private TherapyProgram therapyProgram;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    private Payment payment;

    public Appointment(Date date, Double balance, String status, Therapist therapist1, Patient patient1, TherapyProgram therapyProgram1,Payment payment) {
        this.date = date;
        this.balance = balance;
        this.status = status;
        this.therapist = therapist1;
        this.patient = patient1;
        this.therapyProgram = therapyProgram1;
        this.payment = payment;
    }

    public Appointment(int appointmentId, Double balance, String status) {
        this.appointmentId = appointmentId;
        this.balance = balance;
        this.status = status;
    }
}
