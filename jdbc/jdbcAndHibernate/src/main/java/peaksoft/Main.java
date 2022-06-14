package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
//        Util.connection();
        UserDao userDao = new UserDaoHibernateImpl();
        //userDao.createUsersTable();

//        userDao.createUsersTable();
//        userDao.saveUser("Johny","Depp",(byte)45);
//       userDao.saveUser("Daniel","Radcliffe",(byte)30);
//        userDao.saveUser("Ron","Weasley",(byte)29);
//        userDao.saveUser("Emma","Watson",(byte)18);
//        userDao.dropUsersTable();
//        System.out.println(userDao.getAllUsers());
//        userDao.removeUserById(2);
//        userDao.cleanUsersTable();
    }
}
