package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.InvalidCredentialException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.UserNotFoundException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.CrudDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.SuperDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.User;

public interface UserDAO extends SuperDAO, CrudDAO<User> {
    void initializeAdmin();

    boolean validateUser(String username, String password) throws UserNotFoundException, InvalidCredentialException;

    User findByUsername(String username);
}
