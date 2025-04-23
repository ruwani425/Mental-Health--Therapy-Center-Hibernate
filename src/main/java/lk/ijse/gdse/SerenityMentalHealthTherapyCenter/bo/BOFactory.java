package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOType {
        PATIENT, THERAPIST, APPOINTMENT, THERAPYPROGRAM, USER, PAYMENT, HOME
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case PATIENT:
                return new PatientsBOImpl();
            case THERAPIST:
                return new TherapistsBOImpl();
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case THERAPYPROGRAM:
                return new TherapyProgramBOImpl();
            case USER:
                return new UserBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case HOME:
                return new HomeBOImpl();
            default:
                return null;
        }
    }
}
