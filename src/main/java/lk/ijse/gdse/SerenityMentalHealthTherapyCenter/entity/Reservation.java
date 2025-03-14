package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    private String reservationId;
    private Date reservationDate;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
}
