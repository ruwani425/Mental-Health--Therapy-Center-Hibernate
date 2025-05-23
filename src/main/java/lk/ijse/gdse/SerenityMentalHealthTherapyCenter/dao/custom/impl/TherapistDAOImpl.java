package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.TherapistDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public ArrayList<Therapist> getAllData() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<Therapist> therapists = new ArrayList<>();


        List<Therapist> therapistList = session.createQuery("FROM Therapist ", Therapist.class).list();
        therapists.addAll(therapistList);
        transaction.commit();

        return therapists;
    }

    @Override
    public boolean save(Therapist therapist) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(therapist);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Therapist therapist) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(therapist);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
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
    public boolean delete(Therapist id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.remove(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
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
    public ArrayList<Therapist> search(Therapist newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Therapist findById(Therapist entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Therapist patient = session.get(Therapist.class, entity.getTherapistId());
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
        Query<Integer> query = session.createQuery("SELECT t.id FROM Therapist t", Integer.class);
        return query.list();
    }

    @Override
    public long getTotalTherapistsCount() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        long count = 0;

        try {
            Query<Long> query = session.createQuery("SELECT COUNT(t) FROM Therapist t", Long.class);
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

    @Override
    public List<Integer> getTherapistsByProgramId(Integer selectedProgramId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Integer> therapistIds = new ArrayList<>();

        try {
            Query<Integer> query = session.createQuery(
                    "SELECT t.therapistId FROM Therapist t WHERE t.programID = :programId", Integer.class);
            query.setParameter("programId", selectedProgramId);
            therapistIds = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return therapistIds;
    }
}
