
<%@ page import="com.siit.studentRepoExample.model.Student, com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.model.Course" %>

<%
  String name = request.getParameter("name");
  String email = request.getParameter("email");

  Student student = new Student(name, email);

  JpaStudentRepository studentRepo = new JpaStudentRepository();

  studentRepo.addStudent(student);

%>

  <meta http-equiv="Refresh" content="0; url='/world" />