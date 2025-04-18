package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.AppointmentBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.DAOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.PatientsDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapistDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapyProgramDAO;

import java.util.List;

public class AppointmentBOImpl implements AppointmentBO {


    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    PatientsDAO patientDAO = (PatientsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);
    TherapyProgramDAO programDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPYPROGRAM);

    @Override
    public List<String> getTherapistIds() {
        return therapistDAO.getAllIds();
    }

    @Override
    public List<String> getPatientIds() {
        return patientDAO.getAllIds();
    }

    @Override
    public List<String> getProgramIds() {
        return programDAO.getAllIds();
    }
}
