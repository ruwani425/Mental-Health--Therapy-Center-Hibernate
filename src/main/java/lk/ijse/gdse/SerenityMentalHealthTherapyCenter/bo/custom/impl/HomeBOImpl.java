package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.HomeBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.DAOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.PatientsDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapistDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapyProgramDAO;

public class HomeBOImpl implements HomeBO {

    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    PatientsDAO patientsDAO = (PatientsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);
    TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPYPROGRAM);

    @Override
    public long getPatientCount() {
        return patientsDAO.getPatientCount();
    }

    @Override
    public long getTotalProgramsCount() {
        return therapyProgramDAO.getTotalProgramsCount();
    }

    @Override
    public long getTotalTherapistsCount() {
        return therapistDAO.getTotalTherapistsCount();
    }
}
