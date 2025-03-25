package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.PatientsBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.PatientDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.PatientTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientsViewController implements Initializable {

    @FXML
    private ComboBox cmbGender;

    @FXML
    private TableView<PatientTM> tblPatient;

    @FXML
    private TableColumn<PatientTM, Integer> colPatientId;

    @FXML
    private TableColumn<PatientTM, String> colName;

    @FXML
    private TableColumn<PatientTM, String> colAddress;

    @FXML
    private TableColumn<PatientTM, Date> colDateOfBirth;

    @FXML
    private TableColumn<PatientTM, String> colEmail;

    @FXML
    private TableColumn<PatientTM, String> colPhone;

    @FXML
    private TableColumn<PatientTM, String> colTherapyProgram;

    @FXML
    private JFXTextField txtPatientName;

    @FXML
    private JFXTextField txtPatientAddress;

    @FXML
    private JFXTextField txtPatientPhone;

    @FXML
    private JFXTextField txtPatientEmail;

    @FXML
    private DatePicker datePickerDob;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TextField txtSearch;

    private final PatientsBO patientsBO = (PatientsBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateGender();
    }

    private void populateGender() {
        cmbGender.getItems().addAll("Male", "Female", "Other");
    }

    @FXML
    void btnDeletePatientOnAction(ActionEvent event) {

    }

    @FXML
    void btnSavePatientOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = txtPatientName.getText();
        String address = txtPatientAddress.getText();
        String phone = txtPatientPhone.getText();
        String email = txtPatientEmail.getText();
        String gender = cmbGender.getSelectionModel().getSelectedItem().toString();
        String dateOfBirth = datePickerDob.getValue().toString();

        PatientDTO patientDTO = new PatientDTO(name, address, gender, dateOfBirth, email, phone);

        boolean isSaved = patientsBO.savePatient(patientDTO);
    }

    @FXML
    void btnUpdatePatientOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(KeyEvent event) {

    }
}
