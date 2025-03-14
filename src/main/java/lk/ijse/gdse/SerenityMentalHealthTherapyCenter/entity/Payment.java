package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Payment {
    @Id
    private String paymentId;
    private String status;
    private Double cost;
    private Date paymentDate;
}
