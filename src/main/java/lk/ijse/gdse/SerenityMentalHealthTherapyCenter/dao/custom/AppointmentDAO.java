package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.CrudDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.SuperDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.AppointmentDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AppointmentDAO extends SuperDAO, CrudDAO<Appointment> {
}
