package server.dboperation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import server.Main;

import java.util.List;

public class UserHqlSelector {
    public static List exec(String userName) {
        List userData;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("FROM UsersPersistentClass WHERE userName = :USER_NAME");
            query.setParameter("USER_NAME", userName);
            userData = query.list();
        } catch (HibernateException he) {
            Main.getLogger().error("Hibernate exception in UserHQLSelector class: ", he);
            return null;
        }

        return userData;
    }
}
