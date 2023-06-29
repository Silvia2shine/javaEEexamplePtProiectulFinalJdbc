package com.siit.studentRepoExample.servlet;

import com.google.gson.Gson;
import com.siit.studentRepoExample.model.dto.CreateStudentDto;
import com.siit.studentRepoExample.repository.JpaStudentRepository;
import com.siit.studentRepoExample.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "StudentsApi", urlPatterns = "/students-api")
public class StudentApiServlet extends HttpServlet {

    private final Gson gson = new Gson();
    private final StudentService studentService = new StudentService(new JpaStudentRepository());

    private final JpaStudentRepository studentRepository = new JpaStudentRepository();


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        var studentsList = studentRepository.getAllStudents();
        String jsonOutput =  gson.toJson(studentsList);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonOutput);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        CreateStudentDto studentDto = new CreateStudentDto();
        studentDto.setName(name);
        studentDto.setEmail(email);
        studentService.addStudent(studentDto);
    }
}