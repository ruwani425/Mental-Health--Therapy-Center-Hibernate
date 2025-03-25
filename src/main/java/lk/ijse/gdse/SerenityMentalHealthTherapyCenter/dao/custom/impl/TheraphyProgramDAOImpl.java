package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;

public class TheraphyProgramDAOImpl implements TherapyProgramDAO {
    @Override
    public ArrayList<TherapyProgram> getAllData() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(TherapyProgram Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(TherapyProgram Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(TherapyProgram id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<TherapyProgram> search(TherapyProgram newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public TherapyProgram findById(TherapyProgram entity) throws SQLException {
        return null;
    }
}
