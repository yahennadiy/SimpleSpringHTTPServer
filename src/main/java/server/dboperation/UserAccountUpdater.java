package server.dboperation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import server.Main;
import server.persistentclasses.UsersPersistentClass;

public class UserAccountUpdater {
    public static String exec(UsersPersistentClass user, String userName, String firstName, String lastName) {
        String result;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            session.update(user);
            tx.commit();
            result = "Userdata of " + userName + " has updated";
        } catch (HibernateException he) {
            Main.getLogger().error("Hibernate exception in UserAccountUpdater class:", he);
            result = "Database operation error. Userdata of " + userName + " has not updated";
        }

        return result;
    }
}
