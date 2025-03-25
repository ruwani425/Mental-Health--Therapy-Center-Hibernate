package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.TherapyProgramTM;

public class TheraphyProgramViewController {
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
    private Label lblProgramID;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSave;

    @FXML
    void btnDeleteProgramOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveProgramOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateProgramOnAction(ActionEvent event) {

    }
}
