package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.AppointmentBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.DAOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.*;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.AppointmentDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBOImpl implements AppointmentBO {


    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    PatientsDAO patientDAO = (PatientsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);
    TherapyProgramDAO programDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPYPROGRAM);
    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.APPOINTMENT);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);


    @Override
    public List<Integer> getTherapistIds() {
        return therapistDAO.getAllIds();
    }

    @Override
    public List<Integer> getPatientIds() {
        return patientDAO.getAllIds();
    }

    @Override
    public List<Integer> getProgramIds() {
        return programDAO.getAllIds();
    }

    @Override
    public double getProgramFee(String programId) {
        return programDAO.getFeeById(programId);
    }

    @Override
    public boolean saveAppointment(AppointmentDTO dto) throws SQLException, ClassNotFoundException {

        String id = dto.getPatientId();
        Patient patient = new Patient();
        patient.setPatientId(Integer.parseInt(id));

        String therapistId = dto.getTherapistId();
        Therapist therapist = new Therapist();
        therapist.setTherapistId(Integer.parseInt(therapistId));

        String programID = dto.getProgramId();
        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setProgramId(Integer.parseInt(programID));

        Patient patient1 = patientDAO.findById(patient);
        Therapist therapist1 = therapistDAO.findById(therapist);
        TherapyProgram therapyProgram1 = programDAO.findById(therapyProgram);

        Payment payment = new Payment();
        payment.setPaymentDate(dto.getDate());
        payment.setAmount(dto.getBalance());
        payment.setStatus(dto.getStatus());

        try {
            return appointmentDAO.save(new Appointment(
                    dto.getDate(),
                    dto.getBalance(),
                    dto.getStatus(),
                    therapist1,
                    patient1,
                    therapyProgram1,
                    payment
            ));

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() throws SQLException, ClassNotFoundException {
        List<Appointment> allAppointments = appointmentDAO.getAllData();
        List<AppointmentDTO> dtoList = new ArrayList<>();

        for (Appointment ap : allAppointments) {
            AppointmentDTO dto = new AppointmentDTO(
                    ap.getAppointmentId(),
                    ap.getDate(),
                    ap.getBalance(),
                    ap.getStatus(),
                    String.valueOf(ap.getPatient().getPatientId()),
                    String.valueOf(ap.getTherapist().getTherapistId()),
                    String.valueOf(ap.getTherapyProgram().getProgramId())
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

}
