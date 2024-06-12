package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Petr", "Petrov", (byte) 56);
        System.out.println("User с именем — Petr добавлен в базу данных");
        userService.saveUser("John", "Snow", (byte) 25);
        System.out.println("User с именем — John добавлен в базу данных");
        userService.saveUser("Harry", "Potter", (byte) 17);
        System.out.println("User с именем — Harry добавлен в базу данных");
        userService.saveUser("Jim", "Beam", (byte) 20);
        System.out.println("User с именем — Jim добавлен в базу данных");
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
