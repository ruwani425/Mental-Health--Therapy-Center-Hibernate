package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.SuperBO;

public interface DashBoardBO extends SuperBO {
    long getPatientCount();

    long getTotalProgramsCount();

    long getTotalTherapistsCount();
}
