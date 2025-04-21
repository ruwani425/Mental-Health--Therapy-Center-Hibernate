package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;
    private String status;
    private Double amount;
    private Date paymentDate;

    @OneToOne(mappedBy = "payment")
    private Appointment appointment;
}
