package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.SuperBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.TherapistDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.TherapistTM;

import java.sql.SQLException;
import java.util.List;

public interface TherapistsBO extends SuperBO {
    boolean saveTherapist(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException, PatientPersistException;

    List<TherapistDTO> getAllTherapists() throws SQLException, ClassNotFoundException;

    boolean DeleteTherapist(String id) throws SQLException, ClassNotFoundException;

    boolean updateTherapist(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException;
}
