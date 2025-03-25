package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AppointmentViewController {
    @FXML
    private Label lblAppoinmentId;

    @FXML
    private DatePicker datePickerDate;

    @FXML
    private ComboBox<?> cmbTherapist;

    @FXML
    private ComboBox<?> cmbPatient;

    @FXML
    private ComboBox<?> cmbTherapyProgram;

    @FXML
    private TableView<?> tblAppointment;

    @FXML
    private TableColumn<?, ?> colAppointment;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTherapist;

    @FXML
    private TableColumn<?, ?> colPatient;

    @FXML
    private TableColumn<?, ?> colTherapyProgram;

    @FXML
    private Label lblProgramFee;

    @FXML
    private JFXButton btnPay;

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

    }
}
