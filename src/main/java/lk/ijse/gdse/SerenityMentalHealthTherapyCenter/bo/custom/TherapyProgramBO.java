package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.SuperBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.TherapyProgramDTO;

import java.sql.SQLException;
import java.util.List;

public interface TherapyProgramBO extends SuperBO {
    boolean saveProgram(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException, PatientPersistException;

    List<TherapyProgramDTO> getAllPrograms() throws SQLException, ClassNotFoundException;

    boolean deleteTherapyProgram(String id) throws SQLException, ClassNotFoundException;

    boolean updateProgram(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException;
}
