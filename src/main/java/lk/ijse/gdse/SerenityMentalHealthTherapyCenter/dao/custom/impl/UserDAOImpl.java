package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.impl;

import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dao.custom.UserDAO;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.entity.User;
import lk.ijse.gdse.SerenityMentalHealthTherapyCenter.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
            newAdmin.setPassword("admin123");
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
    public boolean validateUser(String username, String password) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "FROM User WHERE username = :username AND password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            User user = query.uniqueResult();

            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
