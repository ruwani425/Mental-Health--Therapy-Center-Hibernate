package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.SuperBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.MissingFeildException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.PatientDTO;

import java.sql.SQLException;
import java.util.List;

public interface PatientsBO extends SuperBO {
    boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException, MissingFeildException, PatientPersistException;

    List<PatientDTO> getAllPatients() throws SQLException, ClassNotFoundException;

    boolean deletePatient(String id) throws SQLException, ClassNotFoundException;

    boolean UpdatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
}
