package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.UserBO;

import java.io.IOException;
import java.util.Objects;

public class LoginViewController {
    @FXML
    private Label lblFogotPassword;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton loginBtn;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public void NavigateToDashboard(ActionEvent actionEvent) {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        txtUserName.setStyle("-fx-border-color: transparent;");
        txtPassword.setStyle("-fx-border-color: transparent;");

        if (userBO.isUserExists(username, password)) {
            SettingViewController.currentUserName = username;
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
}
