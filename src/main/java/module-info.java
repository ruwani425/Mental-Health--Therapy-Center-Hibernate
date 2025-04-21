module hello.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires static lombok;
    requires net.sf.jasperreports.core;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.management;
    requires jbcrypt;

    opens lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config to jakarta.persistence;
    opens lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity to org.hibernate.orm.core;
    opens lk.ijse.gdse.SerenityMentalHealthTherapyCenter to javafx.fxml;
    opens lk.ijse.gdse.SerenityMentalHealthTherapyCenter.controller to javafx.fxml;
    opens lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto.tm to javafx.base;
    exports lk.ijse.gdse.SerenityMentalHealthTherapyCenter;
}