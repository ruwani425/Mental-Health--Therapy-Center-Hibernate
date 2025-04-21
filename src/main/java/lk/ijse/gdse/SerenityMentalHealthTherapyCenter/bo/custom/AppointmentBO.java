package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.SuperBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.AppointmentDTO;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentBO extends SuperBO {
    List<Integer> getTherapistIds();
    List<Integer> getPatientIds();
    List<Integer> getProgramIds();
    double getProgramFee(String programId);

    boolean saveAppointment(AppointmentDTO dto) throws SQLException, ClassNotFoundException;

    List<AppointmentDTO> getAllAppointments() throws SQLException, ClassNotFoundException;

    boolean updateAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;
}
