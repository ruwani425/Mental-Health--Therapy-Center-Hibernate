package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.SuperBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.PatientDTO;

import java.sql.SQLException;

public interface PatientsBO extends SuperBO {
    boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
}
