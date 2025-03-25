package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TheraphyProgramViewController {
    @FXML
    private TableView<?> tblProgram;

    @FXML
    private TableColumn<?, ?> colProgramID;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private TableColumn<?, ?> colProgramDuration;

    @FXML
    private TableColumn<?, ?> colProgramFee;

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
