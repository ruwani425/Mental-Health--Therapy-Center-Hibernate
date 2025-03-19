package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class DashboardViewController {
    public AnchorPane homeAnchor;
    @FXML
    private ImageView imgHomeBtn;

    @FXML
    private ImageView imgPatientsBtn;

    @FXML
    private ImageView imgBookingsBtn;

    @FXML
    private ImageView imgProgramsBtn;

    @FXML
    private ImageView imgTherapistsBtn;

    @FXML
    private ImageView imgSettingsBtn;

    @FXML
    void onMouseEnterdBtn(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(15);
            glow.setHeight(15);
            glow.setRadius(15);
            icon.setEffect(glow);
        }
    }

    @FXML
    void onMouseExitedBtn(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }

}
