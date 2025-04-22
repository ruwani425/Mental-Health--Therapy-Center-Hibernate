package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.TherapyProgramBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.TherapyProgramTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TheraphyProgramViewController implements Initializable {
    @FXML
    private TableView<TherapyProgramTM> tblProgram;

    @FXML
    private TableColumn<TherapyProgramTM, String> colProgramID;

    @FXML
    private TableColumn<TherapyProgramTM, String> colProgramName;

    @FXML
    private TableColumn<TherapyProgramTM, String> colProgramDuration;

    @FXML
    private TableColumn<TherapyProgramTM, Double> colProgramFee;

    @FXML
    private JFXTextField txtProgramName;

    @FXML
    private JFXTextField txtProgramDuration;

    @FXML
    private JFXTextField txtProgramFee;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSave;

    String id;

    private final TherapyProgramBO therapyProgramBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPYPROGRAM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableListener();
        loadProgramData();
        setCellValueFactory();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void loadProgramData() {
        try {
            List<TherapyProgramDTO> programs = therapyProgramBO.getAllPrograms();
            ObservableList<TherapyProgramTM> programList = FXCollections.observableArrayList();
            for (TherapyProgramDTO dto : programs) {
                programList.add(new TherapyProgramTM(
                        dto.getProgramId(),
                        dto.getProgramName(),
                        dto.getDuration(),
                        dto.getProgramFee()
                ));
            }
            tblProgram.setItems(programList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colProgramDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
    }

    private void setTableListener() {
        tblProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setProgramData(newValue);
            }
        });
    }

    private void setProgramData(TherapyProgramTM program) {
        txtProgramName.setText(program.getProgramName());
        txtProgramDuration.setText(program.getDuration());
        txtProgramFee.setText(String.valueOf(program.getProgramFee()));
        id = String.valueOf(program.getProgramId());
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    @FXML
    void btnDeleteProgramOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isDeleted = therapyProgramBO.deleteTherapyProgram(id);

        if (isDeleted) {
            loadProgramData();
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Therapy Program Deleted").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "failed to delete therapy program").show();
        }
    }

    private void clearFields() {
        txtProgramName.clear();
        txtProgramDuration.clear();
        txtProgramFee.clear();
        id = null;
    }

    private boolean validateFields() {
        String programName = txtProgramName.getText();
        String programDuration = txtProgramDuration.getText();
        String programFeeText = txtProgramFee.getText();

        boolean isValid = true;

        // Reset field styles
        txtProgramName.setStyle("-fx-border-color: transparent;");
        txtProgramDuration.setStyle("-fx-border-color: transparent;");
        txtProgramFee.setStyle("-fx-border-color: transparent;");

        // Validate program name
        if (programName.isEmpty()) {
            txtProgramName.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        // Validate program duration
        if (programDuration.isEmpty()) {
            txtProgramDuration.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        // Validate program fee
        double programFee = 0.0;
        if (programFeeText.isEmpty()) {
            txtProgramFee.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            try {
                programFee = Double.parseDouble(programFeeText);
            } catch (NumberFormatException e) {
                txtProgramFee.setStyle("-fx-border-color: red;");
                isValid = false;
            }
        }

        if (!isValid) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields correctly!").show();
        }

        return isValid;
    }


    @FXML
    void btnSaveProgramOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, PatientPersistException {

        if (!validateFields()) {
            return;
        }

        String programName = txtProgramName.getText();
        String programDuration = txtProgramDuration.getText();
        String programFee = txtProgramFee.getText();

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(programName, programDuration, programFee);

        boolean isSaved = therapyProgramBO.saveProgram(therapyProgramDTO);

        if (isSaved) {
            loadProgramData();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Program Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "program not saved").show();
        }
    }

    @FXML
    void btnUpdateProgramOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (!validateFields()) {
            return;
        }

        String programName = txtProgramName.getText();
        String programDuration = txtProgramDuration.getText();
        double programFee = Double.parseDouble(txtProgramFee.getText());
        int programID = Integer.parseInt(id);

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(programID, programName, programDuration, programFee);

        boolean isUpdated = therapyProgramBO.updateProgram(therapyProgramDTO);

        if (isUpdated) {
            loadProgramData();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Program Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "program not Updated").show();
        }
    }
}
