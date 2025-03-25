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
import javafx.scene.control.TextField;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.TherapistTM;

import java.sql.Date;

public class TherapistsViewController {
    @FXML
    private TableView<TherapistTM> tblTherapist;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistId;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistName;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistAddress;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistEmail;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistStatus;

    @FXML
    private TableColumn<TherapistTM, Date> colTherapistDob;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistPhone;

    @FXML
    private JFXTextField txtTherapistName;

    @FXML
    private JFXTextField txtTherapistAddress;

    @FXML
    private JFXTextField txtTherapistEmail;

    @FXML
    private JFXTextField txtTherapistPhone;

    @FXML
    private ComboBox<String> cmbTherapistStatus;

    @FXML
    private Label lblTherapistID;

    @FXML
    private DatePicker datePickerDob;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TextField txtTherapistSearch;

    @FXML
    void btnDeleteTherapistOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveTherapistOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateTherapistOnAction(ActionEvent event) {

    }

}
