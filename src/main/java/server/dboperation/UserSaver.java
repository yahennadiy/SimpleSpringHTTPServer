package server.dboperation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import server.Main;
import server.persistentclasses.UsersPersistentClass;

public class UserSaver {
    private static UsersPersistentClass user = new UsersPersistentClass();
    public static String exec(String[] userDataArr) {
        String userName = userDataArr[0];
        String firstName = userDataArr[1];
        String lastName = userDataArr[2];
        String result;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            user.setUserName(userName);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            session.save(user);
            tx.commit();
            result = "User data of " + userName + " has stored";
        } catch (HibernateException he) {
            Main.getLogger().error("Hibernate exception in UserSaver class:", he);
            result = "Database operation error. User data of " + userName + " has not stored";
        }

        return result;
    }
}