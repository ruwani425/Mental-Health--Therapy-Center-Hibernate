package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.CrudDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.SuperDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Therapist;

import java.util.List;

public interface TherapistDAO extends SuperDAO, CrudDAO<Therapist> {
    List<String> getAllIds();
}
