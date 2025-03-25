package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.TherapySessionBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl.PatientsBOImpl;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl.PaymentBOImpl;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl.TherapySessionBOImpl;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOType {
        PATIENT, THERAPIST, APPOINTMENT, THERAPYPROGRAM, USER, THERAPYSESSION, PAYMENT
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case PATIENT:
                return new PatientsBOImpl();
            case THERAPIST:
                return new TherapySessionBOImpl();
            case APPOINTMENT:
                return new TherapySessionBOImpl();
            case THERAPYPROGRAM:
                return new TherapySessionBOImpl();
            case USER:
                return new UserBOImpl();
            case THERAPYSESSION:
                return new TherapySessionBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            default:
                return null;
        }
    }
}
