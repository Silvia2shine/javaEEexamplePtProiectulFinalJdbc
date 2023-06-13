package com.siit.studentRepoExample.repository;

import com.siit.studentRepoExample.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentRepository {

    public JdbcStudentRepository() {
        getConnection();
    }
    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/students";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    public Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Can't connect to the db" + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setEmail(email);

                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void addStudent(Student student) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO students (name, email) VALUES (?, ?)")) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}