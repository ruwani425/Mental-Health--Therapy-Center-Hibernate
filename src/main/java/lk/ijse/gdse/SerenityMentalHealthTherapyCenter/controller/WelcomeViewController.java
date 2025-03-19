package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class WelcomeViewController {
    public ImageView recipImg;
    public ImageView adminImg;

    public void navigateLoginPageReceptionist(MouseEvent mouseEvent) {
        LoginViewController.selectedRole = "Receptionist";
        loadLoginPage("login-view.fxml", mouseEvent);
    }

    public void onMouseEnterdImg(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void navigateToLoginPageAdmin(MouseEvent mouseEvent) {
        LoginViewController.selectedRole = "Admin";
        loadLoginPage("login-view.fxml", mouseEvent);
    }

    public void onMouseExitedImg(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }
    private void loadLoginPage(String fxmlFile, MouseEvent event) {
        try {
            String fullPath = "view/" + fxmlFile;
            System.out.println("Trying to load: " + fullPath); // Debug log

            java.net.URL fxmlURL = getClass().getResource("/" + fullPath);
            if (fxmlURL == null) {
                throw new RuntimeException("FXML file not found: " + fullPath);
            }

            Parent root = FXMLLoader.load(fxmlURL);
            Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML: " + fxmlFile);
        }
    }

}
