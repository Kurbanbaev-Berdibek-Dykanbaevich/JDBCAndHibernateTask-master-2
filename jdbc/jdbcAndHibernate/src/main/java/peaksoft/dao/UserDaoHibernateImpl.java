package peaksoft.dao;



import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import peaksoft.model.User;
import peaksoft.util.Util;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {




    }

    @Override
    public void createUsersTable() {
        String SQL ="CREATE TABLE  IF NOT EXISTS users"+
                "(id BIGSERIAL PRIMARY KEY,"+
                "name VARCHAR(50) NOT NULL,"+
                "last_name VARCHAR(55) NOT NULL,"+
                "age INT2)";

          try{
              SessionFactory sessionFactory = Util.getSessionFactory();
              Session session = sessionFactory.openSession();
              session.beginTransaction();
              session.createSQLQuery(SQL).executeUpdate();
              session.getTransaction().commit();
              session.close();
          }catch (HibernateException e){
              e.printStackTrace();
          }
    }

    @Override
    public void dropUsersTable() {

            SessionFactory sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE users;");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();



    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        SessionFactory sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user =  new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.persist(user);
            session.getTransaction().commit();
            session.close();



    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        List<User>users;
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createQuery("FROM User").getResultList();
        session.getTransaction().commit();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = (Query) session.createQuery("DELETE FROM User ");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }
}
