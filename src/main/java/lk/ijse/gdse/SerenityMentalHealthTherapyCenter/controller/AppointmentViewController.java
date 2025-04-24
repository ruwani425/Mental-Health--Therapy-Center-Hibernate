package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.BOFactory;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.bo.custom.AppointmentBO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.AppointmentDTO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm.AppointmentTM;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Appointment;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AppointmentViewController implements Initializable {

    @FXML
    private TableColumn<AppointmentTM, Button> colPAction;
    @FXML
    private TableColumn<AppointmentTM, Button> colPInvoice;

    @FXML
    private JFXButton searchAppointments;

    @FXML
    private Label lblAdvancePayment;

    @FXML
    private Label lblBalancePayment;

    @FXML
    private TextField txtSearch;

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
        cmbTherapist.setDisable(true);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);
        lblAppointmentDate.setText(formattedDate);

        loadComboBoxes();
        cmbTherapyProgram.setOnAction(event -> {
            loadProgramFee();

            Integer selectedProgramId = cmbTherapyProgram.getValue();
            if (selectedProgramId != null) {
                List<Integer> therapistIds = appointmentBO.getTherapistIdsByProgram(selectedProgramId);
                cmbTherapist.getItems().clear();
                cmbTherapist.getItems().addAll(therapistIds);
                cmbTherapist.setDisable(false);
            }
        });

        txtAdvance.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateBalance();
        });

        colCInvoice.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Invoice");

            {
                btn.setOnAction(event -> {
                    AppointmentTM appointment = getTableView().getItems().get(getIndex());
                    System.out.println("Generating invoice for: " + appointment.getAppointmentId());
                    try {
                        generateInvoice(String.valueOf(appointment.getAppointmentId()));
                    } catch (JRException e) {
                        throw new RuntimeException(e);
                    }
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

        colPInvoice.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Invoice");

            {
                btn.setOnAction(event -> {
                    AppointmentTM appointment = getTableView().getItems().get(getIndex());
                    System.out.println("Generating invoice for: " + appointment.getAppointmentId());
                    try {
                        generateInvoice(String.valueOf(appointment.getAppointmentId()));
                    } catch (JRException e) {
                        throw new RuntimeException(e);
                    }
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

        colPAction.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Cansel");

            {
                btn.setOnAction(event -> {
                    AppointmentTM appointment = getTableView().getItems().get(getIndex());
                    try {
                        appointmentBO.deleteAppointment(appointment.getAppointmentId());
                        loadAppointmentsToTable();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

                btn.setStyle("-fx-background-color: #d31f1f; -fx-text-fill: white; -fx-font-weight: bold;");
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

        tblPendingAppointment.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                AppointmentTM selected = tblPendingAppointment.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    setFormDataFromTable(selected);
                }
            }
        });
    }

    private void generateInvoice(String appointmentId) throws JRException {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Connection connection = session.doReturningWork(conn -> conn);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("appointmentId", appointmentId);

            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/reports/invoice_A4_1.jrxml"));

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

    private boolean isValidForm() {
        if (cmbPatient.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a patient!").show();
            return false;
        }

        if (cmbTherapist.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a therapist!").show();
            return false;
        }

        if (cmbTherapyProgram.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a therapy program!").show();
            return false;
        }

        String advanceText = txtAdvance.getText();
        if (advanceText == null || advanceText.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Advance amount cannot be empty!").show();
            return false;
        }

        try {
            double advance = Double.parseDouble(advanceText);
            double fee = Double.parseDouble(lblProgramFee.getText());

            if (advance < 0) {
                new Alert(Alert.AlertType.WARNING, "Advance amount cannot be negative!").show();
                return false;
            }

            if (advance > fee) {
                new Alert(Alert.AlertType.WARNING, "Advance cannot exceed program fee!").show();
                return false;
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Advance must be a valid number!").show();
            return false;
        }
        return true;
    }

    private void setFormDataFromTable(AppointmentTM appointment) {
        cmbPatient.setValue(Integer.parseInt(appointment.getPatientId()));
        cmbTherapyProgram.setValue(Integer.parseInt(appointment.getProgramId()));

        cmbTherapist.setDisable(false);

        System.out.println(appointment.getTherapistId());
        cmbTherapist.setValue(Integer.parseInt(appointment.getTherapistId()));

        try {
            double fee = appointmentBO.getProgramFee(appointment.getProgramId());
            lblProgramFee.setText(String.valueOf(fee));

            double balance = appointment.getBalance();

            lblBalance.setVisible(false);
            lblBalancePayment.setVisible(false);

            lblAdvancePayment.setText("Balance Payment");

            txtAdvance.setText(String.valueOf(balance));
            txtAdvance.setEditable(false);

            btnPay.setText("Finish");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetForm() {
        txtAdvance.clear();
        txtAdvance.setEditable(true);

        lblAdvancePayment.setText("Advance Payment");
        lblBalance.setVisible(true);
        lblBalancePayment.setVisible(true);
        lblBalance.setText("0.00");
        lblProgramFee.setText("0.00");

        cmbPatient.getSelectionModel().clearSelection();
        cmbTherapist.getSelectionModel().clearSelection();
        cmbTherapyProgram.getSelectionModel().clearSelection();

        btnPay.setText("Pay");
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
        if (btnPay.getText().equals("Pay")) {

            if (!isValidForm()) return;

            double balance = Double.parseDouble(lblBalance.getText());
            Date date = Date.valueOf(LocalDate.now());
            String status = (balance == 0 ? "completed" : "pending");

            double payment = (balance == 0) ? Double.parseDouble(lblProgramFee.getText()) : balance;

            String patientId = cmbPatient.getValue().toString();
            String therapistId = cmbTherapist.getValue().toString();
            String programId = cmbTherapyProgram.getValue().toString();

            AppointmentDTO appointmentDTO = new AppointmentDTO(date, payment, status, patientId, therapistId, programId);
            boolean isSaved = appointmentBO.saveAppointment(appointmentDTO);

            if (isSaved) {
                loadAppointmentsToTable();
                resetForm();
                new Alert(Alert.AlertType.INFORMATION, "Appointment saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save appointment").show();
            }

        } else {
            AppointmentTM selectedAppointment = tblPendingAppointment.getSelectionModel().getSelectedItem();

            if (selectedAppointment == null) {
                new Alert(Alert.AlertType.WARNING, "Please select an appointment to finish").show();
                return;
            }

            Date date = Date.valueOf(LocalDate.now());
            String status = "completed";
            String patientId = cmbPatient.getValue().toString();
            String therapistId = cmbTherapist.getValue().toString();
            String programId = cmbTherapyProgram.getValue().toString();
            double balance = Double.parseDouble(lblProgramFee.getText());

            AppointmentDTO appointmentDTO = new AppointmentDTO(selectedAppointment.getAppointmentId(), date, balance, status, patientId, therapistId, programId);
            boolean isUpdated = appointmentBO.updateAppointment(appointmentDTO);

            if (isUpdated) {
                loadAppointmentsToTable();
                resetForm();
                new Alert(Alert.AlertType.INFORMATION, "Appointment updated").show();
            } else {
                resetForm();
                new Alert(Alert.AlertType.ERROR, "Failed to update appointment").show();
            }
        }
    }

    @FXML
    void searchAppointmentOnAction(KeyEvent keyEvent) {
//        String text = txtSearch.getText();
//        List<Appointment>appointmentList=appointmentBO.searchAppointments(text);
    }
}