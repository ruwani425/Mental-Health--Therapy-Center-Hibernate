package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.CrudDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.SuperDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.TherapyProgram;

import java.util.List;

public interface TherapyProgramDAO extends SuperDAO, CrudDAO<TherapyProgram> {
    List<Integer> getAllIds();
    double getFeeById(String programId);
}
