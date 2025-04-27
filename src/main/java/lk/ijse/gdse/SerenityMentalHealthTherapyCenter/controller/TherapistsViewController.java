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
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.TherapistsBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.PatientDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.TherapistDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.PatientTM;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.TherapistTM;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class TherapistsViewController implements Initializable {

    @FXML
    private TableColumn<TherapistTM, Button> colPerfomance;

    @FXML
    private TableColumn<TherapistTM, Integer> colProgram;

    @FXML
    private ComboBox<Integer> comboBoxTherapyProgram;

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
    private DatePicker datePickerDob;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TextField txtTherapistSearch;

    String id;

    private final ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();


    private final TherapistsBO therapistsBO = (TherapistsBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPerformanceButtonColumn();
        populateStatus();
        populatePrograms();
        setTableListener();
        loadTherapistData();
        setCellValueFactory();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }

    private void setPerformanceButtonColumn() {
        colPerfomance.setCellFactory(param -> new TableCell<>() {
            final JFXButton reportBtn = new JFXButton("Report");

            {
                reportBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                reportBtn.setOnAction(event -> {
                    TherapistTM selectedTherapist = getTableView().getItems().get(getIndex());
                    generateTherapistReport(selectedTherapist);
                });
            }

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(reportBtn);
                }
            }
        });
    }

    private void generateTherapistReport(TherapistTM therapist) {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Connection connection = session.doReturningWork(conn -> conn);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("therapistId", therapist.getTherapistId());

            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/reports/perfomance_A4_1.jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate the report.").show();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    private void populatePrograms() {
        List<Integer> programIds = therapistsBO.getAllTherapyProgramIds();
        comboBoxTherapyProgram.getItems().addAll(programIds);
    }

    private boolean validateFields() {
        String name = txtTherapistName.getText();
        String address = txtTherapistAddress.getText();
        String email = txtTherapistEmail.getText();
        String phone = txtTherapistPhone.getText();
        String status = cmbTherapistStatus.getSelectionModel().getSelectedItem();
        Integer program = comboBoxTherapyProgram.getSelectionModel().getSelectedItem();
        boolean isValid = true;

        // Reset field styles
        txtTherapistName.setStyle("-fx-border-color: transparent;");
        txtTherapistAddress.setStyle("-fx-border-color: transparent;");
        txtTherapistEmail.setStyle("-fx-border-color: transparent;");
        txtTherapistPhone.setStyle("-fx-border-color: transparent;");
        cmbTherapistStatus.setStyle("-fx-border-color: transparent;");
        comboBoxTherapyProgram.setStyle("-fx-border-color: transparent;");
        datePickerDob.setStyle("-fx-border-color: transparent;");

        // Validate name
        if (name.isEmpty()) {
            txtTherapistName.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        // Validate address
        if (address.isEmpty()) {
            txtTherapistAddress.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        // Validate email (basic check)
        if (email.isEmpty() || !email.contains("@")) {
            txtTherapistEmail.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        // Validate phone (basic check)
        if (phone.isEmpty() || !phone.matches("\\d{10}")) {
            txtTherapistPhone.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        // Validate status
        if (status == null || status.isEmpty()) {
            cmbTherapistStatus.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        // Validate therapy program
        if (program == null) {
            comboBoxTherapyProgram.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        // Validate date of birth
        if (datePickerDob.getValue() == null) {
            datePickerDob.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        if (!isValid) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields correctly!").show();
        }

        return isValid;
    }


    private void loadTherapistData() {
        therapistTMS.clear();
        try {
            List<TherapistDTO> therapistDTOS = therapistsBO.getAllTherapists();
            for (TherapistDTO dto : therapistDTOS) {
                therapistTMS.add(new TherapistTM(
                        dto.getTherapistId(),
                        dto.getTherapistName(),
                        dto.getEmail(),
                        dto.getPhone(),
                        dto.getAddress(),
                        dto.getDateOfBirth(),
                        dto.getStatus(),
                        dto.getProgramID()
                ));
            }
            tblTherapist.setItems(therapistTMS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colTherapistName.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
        colTherapistAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTherapistEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTherapistDob.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colTherapistPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colTherapistStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programID"));
    }


    private void populateStatus() {
        cmbTherapistStatus.getItems().addAll("Active", "Inactive");
    }

    private void setTableListener() {
        tblTherapist.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnSave.setDisable(true);
                txtTherapistName.setText(newSelection.getTherapistName());
                txtTherapistAddress.setText(newSelection.getAddress());
                txtTherapistEmail.setText(newSelection.getEmail());
                txtTherapistPhone.setText(newSelection.getPhone());
                cmbTherapistStatus.setValue(newSelection.getStatus());
                comboBoxTherapyProgram.setValue(newSelection.getProgramID());
                datePickerDob.setValue(newSelection.getDateOfBirth().toLocalDate());
                id = String.valueOf(newSelection.getTherapistId());

                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            } else {
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);
            }
        });
    }

    @FXML
    void btnDeleteTherapistOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this therapist?", yes, no);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yes) {
            boolean isDeleted = therapistsBO.DeleteTherapist(id);

            if (isDeleted) {
                loadTherapistData();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Therapist Deleted Successfully").show();
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);
                btnSave.setDisable(false);
            } else {
                clearFields();
                btnDelete.setDisable(true);
                btnUpdate.setDisable(true);
                btnSave.setDisable(false);
                new Alert(Alert.AlertType.ERROR, "Therapist Deletion failed").show();
            }
        } else {
            clearFields();
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
            btnSave.setDisable(false);
        }
    }


    @FXML
    void btnSaveTherapistOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, PatientPersistException {

        if (!validateFields()) {
            return;
        }

        String name = txtTherapistName.getText();
        String address = txtTherapistAddress.getText();
        String email = txtTherapistEmail.getText();
        String phone = txtTherapistPhone.getText();
        String status = cmbTherapistStatus.getSelectionModel().getSelectedItem();
        Date dob = Date.valueOf(datePickerDob.getValue());
        int programID = comboBoxTherapyProgram.getSelectionModel().getSelectedItem();

        System.out.println(programID);

        TherapistDTO therapistDTO = new TherapistDTO(name, email, phone, address, dob, status, programID);

        boolean isSaved = therapistsBO.saveTherapist(therapistDTO);
        if (isSaved) {
            loadTherapistData();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Therapist saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Therapist could not be saved").show();
        }
    }

    @FXML
    void btnUpdateTherapistOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (!validateFields()) {
            return;
        }

        String name = txtTherapistName.getText();
        String address = txtTherapistAddress.getText();
        String email = txtTherapistEmail.getText();
        String phone = txtTherapistPhone.getText();
        String status = cmbTherapistStatus.getSelectionModel().getSelectedItem();
        Date dob = Date.valueOf(datePickerDob.getValue());
        int therapistId = Integer.parseInt(id);
        int programID = comboBoxTherapyProgram.getSelectionModel().getSelectedItem();

        System.out.println(programID);

        TherapistDTO therapistDTO = new TherapistDTO(therapistId, name, email, phone, address, dob, status, programID);

        boolean isUpdated = therapistsBO.updateTherapist(therapistDTO);

        if (isUpdated) {
            loadTherapistData();
            clearFields();
            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            new Alert(Alert.AlertType.INFORMATION, "Therapist updated Successfully").show();
        } else {
            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Therapist could not be updated").show();
        }
    }

    private void clearFields() {
        txtTherapistName.clear();
        txtTherapistAddress.clear();
        txtTherapistEmail.clear();
        txtTherapistPhone.clear();
        cmbTherapistStatus.setValue(null);
        datePickerDob.setValue(null);
        comboBoxTherapyProgram.setValue(null);
    }
}
