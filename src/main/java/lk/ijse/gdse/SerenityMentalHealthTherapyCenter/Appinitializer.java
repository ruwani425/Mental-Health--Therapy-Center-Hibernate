package lk.ijse.gdse.SerenityMentalHealthTherapyCenter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config.FactoryConfiguration;
import org.hibernate.Session;

import java.util.Objects;

public class Appinitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login-view.fxml")))));
        stage.show();
        stage.resizableProperty().setValue(Boolean.FALSE);
        Session session = FactoryConfiguration.getInstance().getSession();
        session.close();
    }
}
