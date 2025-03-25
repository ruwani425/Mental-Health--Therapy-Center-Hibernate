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

public class TherapistsViewController {
    @FXML
    private TableView<?> tblTherapist;

    @FXML
    private TableColumn<?, ?> colTherapistId;

    @FXML
    private TableColumn<?, ?> colTherapistName;

    @FXML
    private TableColumn<?, ?> colTherapistAddress;

    @FXML
    private TableColumn<?, ?> colTherapistEmail;

    @FXML
    private TableColumn<?, ?> colTherapistStatus;

    @FXML
    private TableColumn<?, ?> colTherapistDob;

    @FXML
    private TableColumn<?, ?> colTherapistPhone;

    @FXML
    private JFXTextField txtTherapistName;

    @FXML
    private JFXTextField txtTherapistAddress;

    @FXML
    private JFXTextField txtTherapistEmail;

    @FXML
    private JFXTextField txtTherapistPhone;

    @FXML
    private ComboBox<?> cmbTherapistStatus;

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
