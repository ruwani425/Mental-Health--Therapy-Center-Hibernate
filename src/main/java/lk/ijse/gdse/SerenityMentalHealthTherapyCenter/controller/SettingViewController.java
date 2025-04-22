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
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.UserBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.UserDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingViewController implements Initializable {

    @FXML
    public Label lblPassword;

    @FXML
    public Label lblNewUserName;

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
    private JFXPasswordField txtUPassword;

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

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblUserName.setText(currentUserName);
        txtUPassword.setVisible(false);
        txtNewUserName.setVisible(false);
        lblNewUserName.setVisible(false);
        lblPassword.setVisible(false);

        checkBoxUserName.setOnAction(e -> {
            boolean isSelected = checkBoxUserName.isSelected();
            txtUPassword.setVisible(isSelected);
            txtNewUserName.setVisible(isSelected);
            lblNewUserName.setVisible(isSelected);
            lblPassword.setVisible(isSelected);
        });

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
            new Alert(Alert.AlertType.ERROR, "Passwords do not match").show();
        }
    }

    @FXML
    void btnSubmitUserOnAction(ActionEvent event) {

    }

    void clearFields() {
        txtUserName.clear();
        txtPassword.clear();
        txtConformPassword.clear();
    }
}
