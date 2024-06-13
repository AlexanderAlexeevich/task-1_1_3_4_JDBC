package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS Users (id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(25), lastName VARCHAR(25), age TINYINT)";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String query = "DROP TABLE IF EXISTS Users";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users (name, lastName, age) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id =?";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Statement statement = Util.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String query = "TRUNCATE TABLE Users";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
