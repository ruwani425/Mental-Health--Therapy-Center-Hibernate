package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.HomeBO;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblProgramsTotal;
    @FXML
    private Label lblPatientsTotal;
    @FXML
    private Label lblTherapistTotal;

    HomeBO homeBO = (HomeBO) BOFactory.getInstance().getBO(BOFactory.BOType.HOME);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblDate.setText(java.time.LocalDate.now().toString());

        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                new javafx.animation.KeyFrame(javafx.util.Duration.seconds(1), event -> {
                    java.time.LocalTime currentTime = java.time.LocalTime.now();
                    lblTime.setText(currentTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
                })
        );
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();

        long patientCount = homeBO.getPatientCount();
        lblPatientsTotal.setText(String.valueOf(patientCount));

        long totalPrograms = homeBO.getTotalProgramsCount();
        lblProgramsTotal.setText(String.valueOf(totalPrograms));

        long totalTherapists = homeBO.getTotalTherapistsCount();
        lblTherapistTotal.setText(String.valueOf(totalTherapists));
    }

}
