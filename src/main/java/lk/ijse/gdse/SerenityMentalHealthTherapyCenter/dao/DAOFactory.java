package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {

        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType {
        PATIENT, THERAPIST, APPOINTMENT, THERAPYPROGRAM, USER, THERAPYSESSION, PAYMENT
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case PATIENT:
                return new PatientsDAOImpl();
            case THERAPIST:
                return new TherapistDAOImpl();
            case APPOINTMENT:
                return new AppointmentDAOImpl();
            case THERAPYPROGRAM:
                return new TheraphyProgramDAOImpl();
            case USER:
                return new UserDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            default:
                return null;
        }
    }
}
