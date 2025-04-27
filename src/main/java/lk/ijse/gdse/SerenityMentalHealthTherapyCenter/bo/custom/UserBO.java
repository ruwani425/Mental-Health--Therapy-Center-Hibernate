package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.SuperBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.InvalidCredentialException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.UserNotFoundException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.UserDTO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    void initializeAdmin();

    boolean isUserExists(String username, String password) throws UserNotFoundException, InvalidCredentialException;

    boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    UserDTO getUserByUsername(String username);
}
