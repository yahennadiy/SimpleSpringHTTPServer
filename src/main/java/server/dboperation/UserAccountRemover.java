package server.dboperation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import server.Main;
import server.persistentclasses.UsersPersistentClass;

public class UserAccountRemover {
    public static String exec(UsersPersistentClass user) {
        String result;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            result = "Userdata of " + user.getUserName() + " has removed";
        } catch (HibernateException he) {
            Main.getLogger().error("Hibernate exception in UserAccountRemover class:", he);
            result = "Database operation error. Userdata of " + user.getUserName() + " has not removed";
        }

        return result;
    }
}
