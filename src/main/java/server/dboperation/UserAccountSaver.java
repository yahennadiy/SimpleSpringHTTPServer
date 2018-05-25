package server.dboperation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import server.Main;
import server.persistentclasses.UsersPersistentClass;

public class UserAccountSaver {
    private static UsersPersistentClass user = new UsersPersistentClass();
    public static String exec(String userName, String firstName, String lastName) {
        String result;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            user.setUserName(userName);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            session.save(user);
            tx.commit();
            result = "Userdata of " + userName + " has stored";
        } catch (HibernateException he) {
            Main.getLogger().error("Hibernate exception in UserAccountSaver class:", he);
            result = "Database operation error. Userdata of " + userName + " has not stored";
        }

        return result;
    }
}
