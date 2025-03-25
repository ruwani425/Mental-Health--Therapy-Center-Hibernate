package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.SuperBO;

public interface UserBO extends SuperBO {
    void initializeAdmin();

    boolean isUserExists(String username, String password);
}
