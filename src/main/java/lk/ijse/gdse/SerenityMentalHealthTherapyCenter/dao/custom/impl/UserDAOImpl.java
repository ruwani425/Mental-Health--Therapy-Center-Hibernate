package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.InvalidCredentialException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.customexception.UserNotFoundException;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.UserDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.User;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public void initializeAdmin() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<User> query = session.createQuery("FROM User WHERE role = :role", User.class);
        query.setParameter("role", "admin");
        User adminUser = query.uniqueResult();

        if (adminUser == null) {
            User newAdmin = new User();
            newAdmin.setUsername("admin");
            String hashedPassword = BCrypt.hashpw("admin123", BCrypt.gensalt());
            newAdmin.setPassword(hashedPassword);
            newAdmin.setRole("admin");
            session.persist(newAdmin);
            System.out.println("Admin user created.");
        } else {
            System.out.println("Admin user already exists.");
        }

        transaction.commit();
        session.close();
    }


    @Override
    public boolean validateUser(String username, String password) throws UserNotFoundException, InvalidCredentialException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "FROM User WHERE username = :username";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("username", username);

        User user = query.uniqueResult();
//
//            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
//                return true;
//            }
//            return false;

        if (user == null) {
            throw new UserNotFoundException("user not found.");
        }
        if (BCrypt.checkpw(password, user.getPassword())) {
            return true;
        }else {
            throw new InvalidCredentialException("invalid credential.");
        }
    }

    public User findByUsername(String username) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "FROM User WHERE username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);

            return query.uniqueResult(); // null nam user naha
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<User> getAllData() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(user);
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
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(user);
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
    public boolean delete(User id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<User> search(User newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User findById(User entity) throws SQLException {
        return null;
    }
}
