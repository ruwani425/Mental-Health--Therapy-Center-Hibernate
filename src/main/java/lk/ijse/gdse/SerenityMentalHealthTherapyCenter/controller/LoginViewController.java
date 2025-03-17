package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginViewController {
    public Label lblCreateAccount;
    public Label lblFogotPassword;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXButton loginBtn;

    public void NavigateToDashboard(ActionEvent actionEvent) {

    }

    public void navigateTOCreateAccount(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/create-account.fxml")));

            Stage stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Create Account page!");
        }
    }
}
