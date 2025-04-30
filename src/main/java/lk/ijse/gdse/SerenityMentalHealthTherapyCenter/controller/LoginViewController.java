package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.UserBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.InvalidCredentialException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.UserNotFoundException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Appointment;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private ImageView imgClosedEye;
    @FXML
    private JFXTextField txtShowPassword;
    @FXML
    private ImageView imgOpenEye;
    @FXML
    private Label lblFogotPassword;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton loginBtn;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtShowPassword.setVisible(false);
        imgOpenEye.setVisible(false);
    }

    public void NavigateToDashboard(ActionEvent actionEvent) {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        txtUserName.setStyle("-fx-border-color: transparent;");
        txtPassword.setStyle("-fx-border-color: transparent;");

        UserDTO userDTO = userBO.getUserByUsername(username);

        try {
            if (userBO.isUserExists(username, password)) {
                SettingViewController.currentUserName = username;
                DashboardViewController.role = userDTO.getRole();
                SettingViewController.userRole = userDTO.getRole();
                AppointmentViewController.role = userDTO.getRole();
                loadDashboard("dashboard-view.fxml");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Invalid username or password");
                alert.setContentText("Please check your credentials and try again.");
                alert.showAndWait();

                txtUserName.clear();
                txtPassword.clear();
                txtUserName.setStyle("-fx-border-color: red;");
                txtPassword.setStyle("-fx-border-color: red;");
            }
        } catch (UserNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (InvalidCredentialException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadDashboard(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/" + fxmlFile)));
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void hidePasswordOnAction(KeyEvent keyEvent) {
        String validPassword = txtPassword.getText();
        txtShowPassword.setText(validPassword);
    }

    @FXML
    private void showPasswordOnAction(KeyEvent keyEvent) {
        String validPassword = txtShowPassword.getText();
        txtPassword.setText(validPassword);
    }

    @FXML
    private void closeEyeClickOnAction(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(true);
        imgOpenEye.setVisible(true);
        imgClosedEye.setVisible(false);
        txtPassword.setVisible(false);
    }

    @FXML
    private void openEyeClickOnAction(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(false);
        imgOpenEye.setVisible(false);
        imgClosedEye.setVisible(true);
        txtPassword.setVisible(true);
    }
}
