module hello.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config to jakarta.persistence;
    opens lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity to org.hibernate.orm.core;
    opens lk.ijse.gdse.SerenityMentalHealthTherapyCenter to javafx.fxml;
    opens lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller to javafx.fxml;

    exports lk.ijse.gdse.SerenityMentalHealthTherapyCenter;
}