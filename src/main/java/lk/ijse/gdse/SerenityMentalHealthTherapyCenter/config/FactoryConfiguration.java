package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Properties properties = new Properties();

        // Load hibernate.properties from classpath (src/main/resources)
        try (InputStream input = FactoryConfiguration.class.getClassLoader().getResourceAsStream("hibernate.properties")) {
            if (input == null) {
                throw new RuntimeException("'hibernate.properties' file not found in classpath (src/main/resources).");
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(" Failed to load 'hibernate.properties' file.", e);
        }

        // Build the SessionFactory
        try {
            Configuration configuration = new Configuration().addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Therapist.class)
                    .addAnnotatedClass(TherapyProgram.class)
                    .addAnnotatedClass(Appointment.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(" Failed to build SessionFactory.", e);
        }
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            synchronized (FactoryConfiguration.class) {
                if (factoryConfiguration == null) {
                    factoryConfiguration = new FactoryConfiguration();
                }
            }
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        if (sessionFactory != null) {
            return sessionFactory.openSession();
        } else {
            throw new IllegalStateException(" SessionFactory is not initialized.");
        }
    }
}
