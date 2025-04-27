package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.UserBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.InvalidCredentialException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.UserNotFoundException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.DAOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.UserDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public void initializeAdmin() {
        userDAO.initializeAdmin();
    }

    @Override
    public boolean isUserExists(String username, String password) throws UserNotFoundException, InvalidCredentialException {
        return userDAO.validateUser(username, password);
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        User user = new User(userDTO.getUsername(), hashedPassword, userDTO.getRole());
        return userDAO.save(user);
    }

    @Override
    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        User user = new User(userDTO.getUserId(), userDTO.getRole(), userDTO.getUsername(), hashedPassword);
        return userDAO.update(user);
    }

    public UserDTO getUserByUsername(String username) {
        User user = userDAO.findByUsername(username);
        if (user != null) {
            return new UserDTO(user.getUserId(), user.getRole(), user.getUsername(), user.getPassword());
        }
        return null;
    }

}
