package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.AppointmentBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.AppointmentTM;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AppointmentViewController implements Initializable {

    @FXML
    private DatePicker datePickerDate;

    @FXML
    private ComboBox<String> cmbTherapist;

    @FXML
    private ComboBox<String> cmbPatient;

    @FXML
    private ComboBox<String> cmbTherapyProgram;

    @FXML
    private Label lblProgramFee;

    @FXML
    private JFXButton btnPay;

    @FXML
    private Label lblBalance;

    @FXML
    private JFXTextField txtAdvance;

    @FXML
    private TableView<AppointmentTM> tblPendingAppointment;

    @FXML
    private TableColumn<AppointmentTM, String> colPAppointmentId;

    @FXML
    private TableColumn<AppointmentTM, Date> colPDate;

    @FXML
    private TableColumn<AppointmentTM, String> colPTherapist;

    @FXML
    private TableColumn<AppointmentTM, String> colPPatient;

    @FXML
    private TableColumn<AppointmentTM, String> colPTherapyProgram;

    @FXML
    private TableColumn<AppointmentTM, Double> colPBalance;

    @FXML
    private TableView<AppointmentTM> tblCompletedAppoinment;

    @FXML
    private TableColumn<AppointmentTM, String> colCAppointmentId;

    @FXML
    private TableColumn<AppointmentTM, Date> colCDate;

    @FXML
    private TableColumn<AppointmentTM, String> colCTherapist;

    @FXML
    private TableColumn<AppointmentTM, String> colCPatient;

    @FXML
    private TableColumn<AppointmentTM, String> colCTheraphyProgram;

    @FXML
    private TableColumn<AppointmentTM, Double> colCBalance;

    @FXML
    private TableColumn<AppointmentTM, Button> colCInvoice;

    AppointmentBO appointmentBO = (AppointmentBO) BOFactory.getInstance().getBO(BOFactory.BOType.APPOINTMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxes();
    }

    private void loadComboBoxes() {
        try {
            cmbTherapist.getItems().addAll(appointmentBO.getTherapistIds());
            cmbPatient.getItems().addAll(appointmentBO.getPatientIds());
            cmbTherapyProgram.getItems().addAll(appointmentBO.getProgramIds());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load data").show();
        }
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

    }

}
