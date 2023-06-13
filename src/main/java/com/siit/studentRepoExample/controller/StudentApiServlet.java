package com.siit.studentRepoExample.controller;

import com.google.gson.Gson;
import com.siit.studentRepoExample.repository.JpaStudentRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StudentsApi", urlPatterns = "/getStudentsApi")
public class StudentApiServlet extends HttpServlet {

    private final Gson gson = new Gson();
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
}