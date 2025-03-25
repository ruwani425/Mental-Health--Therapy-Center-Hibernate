package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapistDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Therapist;

import java.sql.SQLException;
import java.util.ArrayList;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public ArrayList<Therapist> getAllData() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Therapist Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Therapist Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Therapist id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<Therapist> search(Therapist newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Therapist findById(Therapist entity) throws SQLException {
        return null;
    }
}
