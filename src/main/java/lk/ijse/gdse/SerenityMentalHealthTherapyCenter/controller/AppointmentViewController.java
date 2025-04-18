package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AppointmentViewController {

    @FXML
    private DatePicker datePickerDate;

    @FXML
    private ComboBox<?> cmbTherapist;

    @FXML
    private ComboBox<?> cmbPatient;

    @FXML
    private ComboBox<?> cmbTherapyProgram;

    @FXML
    private Label lblProgramFee;

    @FXML
    private JFXButton btnPay;

    @FXML
    private Label lblBalance;

    @FXML
    private JFXTextField txtAdvance;

    @FXML
    private TableView<?> tblPendingAppointment;

    @FXML
    private TableColumn<?, ?> colPAppointmentId;

    @FXML
    private TableColumn<?, ?> colPDate;

    @FXML
    private TableColumn<?, ?> colPTherapist;

    @FXML
    private TableColumn<?, ?> colPPatient;

    @FXML
    private TableColumn<?, ?> colPTherapyProgram;

    @FXML
    private TableColumn<?, ?> colPBalance;

    @FXML
    private TableView<?> tblCompletedAppoinment;

    @FXML
    private TableColumn<?, ?> colCAppointmentId;

    @FXML
    private TableColumn<?, ?> colCDate;

    @FXML
    private TableColumn<?, ?> colCTherapist;

    @FXML
    private TableColumn<?, ?> colCPatient;

    @FXML
    private TableColumn<?, ?> colCTheraphyProgram;

    @FXML
    private TableColumn<?, ?> colCBalance;

    @FXML
    private TableColumn<?, ?> colCInvoice;

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

    }
}
