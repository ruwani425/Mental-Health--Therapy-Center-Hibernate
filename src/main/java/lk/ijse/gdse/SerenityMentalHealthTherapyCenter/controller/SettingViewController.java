package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.UserBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.UserDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingViewController implements Initializable {

    @FXML
    private Label lblNewUserName;

    @FXML
    private Tab addUserTabPane;

    @FXML
    private CheckBox checkBoxUserName;

    @FXML
    private JFXPasswordField txtCurrentPassword;

    @FXML
    private JFXPasswordField txtNewPassword;

    @FXML
    private JFXPasswordField txtConformNewPassword;

    @FXML
    private JFXTextField txtNewUserName;

    @FXML
    private Label lblUserName;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtConformPassword;

    @FXML
    private JFXButton btnAddUser;

    public static String currentUserName;
    public static String userRole;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (userRole.equals("Receptionist")) {
            addUserTabPane.setDisable(true);
        }

        lblUserName.setText(currentUserName);
        lblNewUserName.setVisible(false);
        txtNewUserName.setVisible(false);

        checkBoxUserName.setOnAction(e -> {
            boolean isSelected = checkBoxUserName.isSelected();
            txtNewUserName.setVisible(isSelected);
            lblNewUserName.setVisible(isSelected);
        });

    }

    private void setFieldBorderColorRed(JFXPasswordField passwordField) {
        passwordField.setStyle("-fx-border-color: red;");
    }

    private void resetFieldBorderColor(JFXPasswordField passwordField) {
        passwordField.setStyle("-fx-border-color: transparent;");
    }

    @FXML
    void btnAddUserOnAction(ActionEvent event) throws PatientPersistException, SQLException, ClassNotFoundException {
        if (txtPassword.getText().equals(txtConformPassword.getText())) {
            UserDTO userDTO = new UserDTO(txtUserName.getText(), txtPassword.getText(), "Receptionist");
            boolean isSaved = userBO.saveUser(userDTO);

            if (isSaved) {
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "User added successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "User could not be added").show();
            }
        } else {
            setFieldBorderColorRed(txtPassword);
            setFieldBorderColorRed(txtConformPassword);
            new Alert(Alert.AlertType.ERROR, "Passwords do not match").show();
        }
    }

    @FXML
    void btnSubmitUserOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String currentPassword = txtCurrentPassword.getText();
        String newPassword = txtNewPassword.getText();
        String conformNewPassword = txtConformNewPassword.getText();

        String finalUserName;
        boolean isValid = userBO.isUserExists(currentUserName, currentPassword);
        UserDTO currentUserDTO = userBO.getUserByUsername(currentUserName);

        if (checkBoxUserName.isSelected()) {
            finalUserName = txtNewUserName.getText();
            if (finalUserName == null || finalUserName.trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter new user name").show();
                return;
            }
        } else {
            finalUserName = lblUserName.getText();
        }

        if (isValid && currentUserDTO != null) {
            if (newPassword.equals(conformNewPassword)) {
                UserDTO userDTO = new UserDTO(
                        currentUserDTO.getUserId(),
                        currentUserDTO.getRole(),
                        finalUserName,
                        newPassword
                );

                boolean isUpdated = userBO.update(userDTO);

                if (isUpdated) {
                    clearFields();
                    new Alert(Alert.AlertType.INFORMATION, "User updated successfully").show();
                } else {
                    clearFields();
                    new Alert(Alert.AlertType.ERROR, "User could not be updated").show();
                }
            } else {
                setFieldBorderColorRed(txtNewPassword);
                setFieldBorderColorRed(txtConformNewPassword);
                new Alert(Alert.AlertType.ERROR, "Confirm password does not match").show();
            }
        } else {
            setFieldBorderColorRed(txtCurrentPassword);
            new Alert(Alert.AlertType.ERROR, "Password is incorrect").show();
        }
    }


    void clearFields() {
        txtUserName.clear();
        txtPassword.clear();
        txtConformPassword.clear();
        resetFieldBorderColor(txtPassword);
        resetFieldBorderColor(txtConformPassword);
        resetFieldBorderColor(txtNewPassword);
        resetFieldBorderColor(txtConformNewPassword);
        resetFieldBorderColor(txtCurrentPassword);
    }
}
