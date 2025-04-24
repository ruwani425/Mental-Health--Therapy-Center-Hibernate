package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.AppointmentDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Appointment;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public ArrayList<Appointment> getAllData() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return (ArrayList<Appointment>) session.createQuery("FROM Appointment", Appointment.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean save(Appointment entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Appointment appointment) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(appointment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean existId(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Appointment id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<Appointment> search(Appointment newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Appointment findById(Appointment entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Appointment appointment = session.get(Appointment.class, entity.getAppointmentId());
            transaction.commit();
            return appointment;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
