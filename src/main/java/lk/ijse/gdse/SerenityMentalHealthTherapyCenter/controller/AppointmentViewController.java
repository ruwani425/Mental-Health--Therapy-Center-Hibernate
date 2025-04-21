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
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.AppointmentBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.AppointmentDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.AppointmentTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentViewController implements Initializable {

    @FXML
    private Label lblAppointmentDate;

    @FXML
    private ComboBox<Integer> cmbTherapist;

    @FXML
    private ComboBox<Integer> cmbPatient;

    @FXML
    private ComboBox<Integer> cmbTherapyProgram;

    @FXML
    private Label lblProgramFee;

    @FXML
    private JFXButton btnPay;

    @FXML
    private Label lblBalance;

    @FXML
    private JFXTextField txtAdvance;

    @FXML
    private TableView<AppointmentTM> tblPendingAppointment;

    @FXML
    private TableColumn<AppointmentTM, String> colPAppointmentId;

    @FXML
    private TableColumn<AppointmentTM, Date> colPDate;

    @FXML
    private TableColumn<AppointmentTM, String> colPTherapist;

    @FXML
    private TableColumn<AppointmentTM, String> colPPatient;

    @FXML
    private TableColumn<AppointmentTM, String> colPTherapyProgram;

    @FXML
    private TableColumn<AppointmentTM, Double> colPBalance;

    @FXML
    private TableView<AppointmentTM> tblCompletedAppoinment;

    @FXML
    private TableColumn<AppointmentTM, String> colCAppointmentId;

    @FXML
    private TableColumn<AppointmentTM, Date> colCDate;

    @FXML
    private TableColumn<AppointmentTM, String> colCTherapist;

    @FXML
    private TableColumn<AppointmentTM, String> colCPatient;

    @FXML
    private TableColumn<AppointmentTM, String> colCTheraphyProgram;

    @FXML
    private TableColumn<AppointmentTM, Double> colCBalance;

    @FXML
    private TableColumn<AppointmentTM, Button> colCInvoice;

    AppointmentBO appointmentBO = (AppointmentBO) BOFactory.getInstance().getBO(BOFactory.BOType.APPOINTMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadAppointmentsToTable();
        setCellValueFactory();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);
        lblAppointmentDate.setText(formattedDate);

        loadComboBoxes();
        cmbTherapyProgram.setOnAction(event -> loadProgramFee());

        txtAdvance.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateBalance();
        });

        //add button in invoice column
        colCInvoice.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Invoice");

            {
                btn.setOnAction(event -> {
                    AppointmentTM appointment = getTableView().getItems().get(getIndex());
                    // ➤ Here you can implement the logic to generate the invoice
                    System.out.println("Generating invoice for: " + appointment.getAppointmentId());
                    // Example: openInvoiceWindow(appointment);
                });

                btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
            }

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || getTableView().getItems().get(getIndex()) == null) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

    }

    private void setCellValueFactory() {
        colPAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colPDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPTherapist.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colPPatient.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colPTherapyProgram.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colPBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));

        colCAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colCDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCTherapist.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colCPatient.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colCTheraphyProgram.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colCBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    private void calculateBalance() {
        try {
            String advanceText = txtAdvance.getText();
            if (advanceText == null || advanceText.isEmpty()) {
                lblBalance.setText(String.valueOf(0.00));
                return;
            }

            double advance = Double.parseDouble(advanceText);

            String feeText = lblProgramFee.getText();
            double fee = Double.parseDouble(feeText);

            double balance = fee - advance;
            lblBalance.setText(String.valueOf(balance));
        } catch (NumberFormatException e) {
            lblBalance.setText("Invalid input");
        }
    }

    private void loadProgramFee() {
        Object selectedProgram = cmbTherapyProgram.getValue();
        System.out.println(selectedProgram);
        if (selectedProgram != null) {
            try {
                String programId = String.valueOf(selectedProgram);
                double fee = appointmentBO.getProgramFee(programId);
                lblProgramFee.setText(String.valueOf(fee));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadComboBoxes() {
        try {
            cmbTherapist.getItems().addAll(appointmentBO.getTherapistIds());
            cmbPatient.getItems().addAll(appointmentBO.getPatientIds());
            cmbTherapyProgram.getItems().addAll(appointmentBO.getProgramIds());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load data").show();
        }
    }

    private void loadAppointmentsToTable() {
        try {
            List<AppointmentDTO> appointmentList = appointmentBO.getAllAppointments();

            ObservableList<AppointmentTM> pendingList = FXCollections.observableArrayList();
            ObservableList<AppointmentTM> completedList = FXCollections.observableArrayList();

            for (AppointmentDTO dto : appointmentList) {
                AppointmentTM tm = new AppointmentTM(
                        dto.getAppointmentId(),
                        dto.getDate(),
                        dto.getBalance(),
                        dto.getStatus(),
                        dto.getPatientId(),
                        dto.getTherapistId(),
                        dto.getProgramId()
                );

                if ("completed".equalsIgnoreCase(dto.getStatus())) {
                    completedList.add(tm);
                } else {
                    pendingList.add(tm);
                }
            }

            tblPendingAppointment.setItems(pendingList);
            tblCompletedAppoinment.setItems(completedList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnPaymentOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        double balance = Double.parseDouble(lblBalance.getText());

        if (balance >= 0) {
            Date date = Date.valueOf(LocalDate.now());
            String status = (balance == 0 ? "completed" : "pending");

            String patientId = cmbPatient.getValue().toString();
            String therapistId = cmbTherapist.getValue().toString();
            String programId = cmbTherapyProgram.getValue().toString();

            AppointmentDTO appointmentDTO = new AppointmentDTO(date, balance, status, patientId, therapistId, programId);
            boolean isSaved = appointmentBO.saveAppointment(appointmentDTO);

            if (isSaved) {
                loadAppointmentsToTable();
                new Alert(Alert.AlertType.INFORMATION, "Appointment saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save appointment").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid advance amount. Balance cannot be negative!").show();
        }
    }
}

