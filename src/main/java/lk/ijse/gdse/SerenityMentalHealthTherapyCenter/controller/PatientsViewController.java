package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.PatientsBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.MissingFeildException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.PatientDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.PatientTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientsViewController implements Initializable {

    @FXML
    private JFXTextField txtnicNumber;

    @FXML
    private ComboBox cmbGender;

    @FXML
    private TableView<PatientTM> tblPatient;

    @FXML
    private TableColumn<PatientTM, Integer> colPatientId;

    @FXML
    private TableColumn<PatientTM, String> colNic;

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

    String id;


    private ObservableList<PatientTM> patientList = FXCollections.observableArrayList();

    private final PatientsBO patientsBO = (PatientsBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateGender();
        setTableListener();
        loadPatientData();
        setCellValueFactory();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private boolean validateForm() {
        boolean isValid = true;

        // Name
        if (txtPatientName.getText().trim().isEmpty()) {
            txtPatientName.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtPatientName.setStyle(null);
        }

        // Address
        if (txtPatientAddress.getText().trim().isEmpty()) {
            txtPatientAddress.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtPatientAddress.setStyle(null);
        }

        // Phone (only digits, 10 characters)
        if (!txtPatientPhone.getText().matches("\\d{10}")) {
            txtPatientPhone.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtPatientPhone.setStyle(null);
        }

        // Email (simple pattern)
        if (!txtPatientEmail.getText().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            txtPatientEmail.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtPatientEmail.setStyle(null);
        }

        // NIC
        if (!txtnicNumber.getText().matches("^(?:\\d{9}[vVxX]|\\d{12})$")) {
            txtnicNumber.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            txtnicNumber.setStyle(null);
        }

        // Gender
        if (cmbGender.getSelectionModel().getSelectedItem() == null) {
            cmbGender.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            cmbGender.setStyle(null);
        }

        // Date of Birth
        if (datePickerDob.getValue() == null) {
            datePickerDob.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            datePickerDob.setStyle(null);
        }

        return isValid;
    }

    private void loadPatientData() {
        patientList.clear();
        try {
            List<PatientDTO> patients = patientsBO.getAllPatients();
            for (PatientDTO dto : patients) {
                patientList.add(new PatientTM(
                        dto.getPatientId(),
                        dto.getNic(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getGender(),
                        dto.getDateOfBirth(),
                        dto.getEmail(),
                        dto.getPhoneNumber()
                ));
            }
            tblPatient.setItems(patientList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    private void setTableListener() {
        tblPatient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setPatientData(newValue);
            }
        });
    }

    private void setPatientData(PatientTM patient) {
        txtPatientName.setText(patient.getName());
        txtPatientAddress.setText(patient.getAddress());
        txtPatientPhone.setText(patient.getPhoneNumber());
        txtPatientEmail.setText(patient.getEmail());
        txtnicNumber.setText(patient.getNic());
        cmbGender.setValue(patient.getGender());
        id = String.valueOf(patient.getPatientId());
        datePickerDob.setValue(patient.getDateOfBirth().toLocalDate());
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        btnSave.setDisable(true);
    }


    private void populateGender() {
        cmbGender.getItems().addAll("Male", "Female", "Other");
    }

    @FXML
    void btnDeletePatientOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this patient?", yes, no);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yes) {
            boolean isDeleted = patientsBO.deletePatient(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Patient deleted successfully!").show();
                loadPatientData();
                clearFields();
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);
                btnSave.setDisable(false);
            } else {
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);
                btnSave.setDisable(false);
                new Alert(Alert.AlertType.ERROR, "Failed to delete patient!").show();
            }
        } else {
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
            btnSave.setDisable(false);
        }
    }


    private void clearFields() {
        txtPatientName.clear();
        txtnicNumber.clear();
        txtPatientAddress.clear();
        txtPatientPhone.clear();
        txtPatientEmail.clear();
        cmbGender.getSelectionModel().clearSelection();
        datePickerDob.setValue(null);
        id = null;
    }

    @FXML
    void btnSavePatientOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!validateForm()) {
            new Alert(Alert.AlertType.ERROR, "Please correct the highlighted fields!").show();
            return;
        }

        String name = txtPatientName.getText();
        String address = txtPatientAddress.getText();
        String phone = txtPatientPhone.getText();
        String email = txtPatientEmail.getText();
        String gender = cmbGender.getSelectionModel().getSelectedItem().toString();
        Date dateOfBirth = Date.valueOf(datePickerDob.getValue());
        String nic = txtnicNumber.getText();

        PatientDTO patientDTO = new PatientDTO(nic, name, address, gender, dateOfBirth, email, phone);

        try {
            boolean isSaved = patientsBO.savePatient(patientDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Patient saved successfully!").show();
                clearFields();
                loadPatientData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save patient!").show();
            }
        } catch (MissingFeildException | PatientPersistException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdatePatientOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!validateForm()) {
            new Alert(Alert.AlertType.ERROR, "Please correct the highlighted fields!").show();
            return;
        }

        String name = txtPatientName.getText();
        String address = txtPatientAddress.getText();
        String phone = txtPatientPhone.getText();
        String email = txtPatientEmail.getText();
        String gender = cmbGender.getSelectionModel().getSelectedItem().toString();
        Date dateOfBirth = Date.valueOf(datePickerDob.getValue());
        String nic = txtnicNumber.getText();

        PatientDTO patientDTO = new PatientDTO(id, nic, name, address, gender, dateOfBirth, email, phone);

        boolean isUpdate = patientsBO.UpdatePatient(patientDTO);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Patient updated successfully!").show();
            clearFields();
            loadPatientData();
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            btnSave.setDisable(false);
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update patient!").show();
        }
    }

    @FXML
    void txtSearchOnAction(KeyEvent event) {

    }

}
