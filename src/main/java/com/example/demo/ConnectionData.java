package com.example.demo;

import org.springframework.http.HttpStatus;

import java.sql.*;
import java.time.LocalDate;
import java.sql.SQLException;

public class ConnectionData {

//    private static final String URL = "jdbc:postgresql://172.17.0.1:5432/MyBase";
    private static final String URL = "jdbc:postgresql://localhost:5432/MyBase";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static final String nameTableOne = "table_one";
    private static final String nameTableTwo = "table_two";

    public static User doSelect(String login_name) throws SQLException {

        User user = new User();
        // Установление соединения
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        try {
            String sql = "SELECT * FROM " + nameTableOne + " JOIN " + nameTableTwo + " on " + nameTableOne + ".login = " + nameTableTwo + ".login where " + nameTableOne + ".login = '" + login_name + "';";
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            // Проверка на пользователя
            if (rs.next()) {

                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("passwd"));
                user.setCurrentDate(rs.getDate("currentdate"));
                user.setEmail(rs.getString("email"));

            } else {        // Пользователь отсутствует
                throw new ExceptionService(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            connection.close();
        }
        return user;
    }

    public String doInsert(User user) {

        // Запрос с указанием мест для параметров в виде знака "?"
        String sqlRequest = "INSERT INTO " + nameTableOne + "(login, passwd, currentdate) VALUES (?, ?, ?); \n " +
                "INSERT INTO " + nameTableTwo + " (login, email) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             // Создание запроса.
             PreparedStatement prestmt = connection.prepareStatement(sqlRequest);

        ) {
            // Установка параметров
            prestmt.setString(1, user.getLogin());
            prestmt.setString(2, user.getPassword());
            prestmt.setDate(3, Date.valueOf(LocalDate.now()));
            prestmt.setString(4, user.getLogin());
            prestmt.setString(5, user.getEmail());
            prestmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (e.getMessage().contains("duplicate key value")) {   // Проверка, что пользователь уже создан
                throw new ExceptionService(HttpStatus.BAD_REQUEST);
            }
        }
        return "Успех";
    }
}
