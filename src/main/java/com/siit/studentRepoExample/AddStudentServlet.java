package com.siit.studentRepoExample;

import com.siit.studentRepoExample.model.Student;
import com.siit.studentRepoExample.repository.JdbcStudentRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JdbcStudentRepository repository = new JdbcStudentRepository();
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        Student student = new Student(name, email);
        repository.addStudent(student);
        resp.getOutputStream().println("Added!");

    }


}