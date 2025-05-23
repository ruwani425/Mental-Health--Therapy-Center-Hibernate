package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.CrudDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.SuperDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientsDAO extends SuperDAO, CrudDAO<Patient> {
    List<Integer> getAllIds();

    long getPatientCount();
}
