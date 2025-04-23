package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.PatientPersistException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.PatientsDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientsDAOImpl implements PatientsDAO {
    @Override
    public ArrayList<Patient> getAllData() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<Patient> patients = new ArrayList<>();

        try {
            List<Patient> patientList = session.createQuery("FROM Patient", Patient.class).list();
            patients.addAll(patientList);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return patients;
    }

    @Override
    public boolean save(Patient patientEntity) throws ClassNotFoundException, SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(patientEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new SQLException(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Patient entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(entity);
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
    public boolean delete(Patient id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(id);
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
    public String getNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<Patient> search(Patient newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Patient findById(Patient entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Patient patient = session.get(Patient.class, entity.getPatientId());
            transaction.commit();
            return patient;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Integer> getAllIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Integer> query = session.createQuery("SELECT p.id FROM Patient p", Integer.class);
        return query.list();
    }

    @Override
    public long getPatientCount() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        long count = 0;

        try {
            Query<Long> query = session.createQuery("SELECT COUNT(p.patientId) FROM Patient p", Long.class);
            count = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }
}
