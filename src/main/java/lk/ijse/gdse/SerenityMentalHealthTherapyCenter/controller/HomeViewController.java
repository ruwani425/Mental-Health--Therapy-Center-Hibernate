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
    private Label lblProgramsTotal;
    @FXML
    private Label lblPatientsTotal;
    @FXML
    private Label lblTherapistTotal;

    HomeBO homeBO = (HomeBO) BOFactory.getInstance().getBO(BOFactory.BOType.HOME);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        long patientCount = homeBO.getPatientCount();
        lblPatientsTotal.setText(String.valueOf(patientCount));
        long totalPrograms = homeBO.getTotalProgramsCount();
        lblProgramsTotal.setText(String.valueOf(totalPrograms));
        long totalTherapists = homeBO.getTotalTherapistsCount();
        lblTherapistTotal.setText(String.valueOf(totalTherapists));
    }
}
