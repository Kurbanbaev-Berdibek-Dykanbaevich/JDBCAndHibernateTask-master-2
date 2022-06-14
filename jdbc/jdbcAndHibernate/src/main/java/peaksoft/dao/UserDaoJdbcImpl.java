package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE  IF NOT EXISTS users"+
                "(id BIGSERIAL PRIMARY KEY,"+
                "name VARCHAR(50)NOT NULL,"+
                "lastName VARCHAR(55)NOT NULL,"+
                "age INT2)";
        try(Connection connection = Util.connection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(SQL);
            System.out.println("Table is seccesfully created!");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE users;";
        try(Connection connection = Util.connection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(SQL);
            System.out.println("Table is succesfully dropped");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users(name,last_name,age)VALUES (?,?,?)";
        try(Connection connection = Util.connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            preparedStatement.executeUpdate();
            System.out.println("User successfully added "+ name);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id=?;";
        try(Connection connection = Util.connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("User successfully removed by id");
        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM users;";
        List<User>users = new ArrayList<>();
        try(Connection connection = Util.connection();
        Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM users;";
        try(Connection connection = Util.connection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(SQL);
            System.out.println("Users table successfully cleaned");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}