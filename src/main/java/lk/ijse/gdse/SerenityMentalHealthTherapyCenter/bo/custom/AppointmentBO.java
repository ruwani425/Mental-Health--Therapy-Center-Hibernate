package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.SuperBO;

import java.util.List;

public interface AppointmentBO extends SuperBO {
    List<String> getTherapistIds();
    List<String> getPatientIds();
    List<String> getProgramIds();
}
