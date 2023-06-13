<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.siit.studentRepoExample.repository.JpaStudentRepository" %>
<%@ page import="com.siit.studentRepoExample.repository.CoursesRepository" %>
<%@ page import="com.siit.studentRepoExample.OutOfNameIdeas" %>
<%@ page import="com.siit.studentRepoExample.model.Student" %>
<%@ page import="com.siit.studentRepoExample.model.Course" %>
<html>

 <head>
    <!-- This will make the table look nicer -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Very nice student list</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.21.4/dist/bootstrap-table.min.css">
  </head>
<body>
    <h2>Student List from the index.jsp file</h2>
    <table border="1" class="table table-striped table-hover w-50 p-3">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Courses</th>
        </tr>
        <%
       //     JdbcStudentRepository repository = new JdbcStudentRepository();
            JpaStudentRepository repository = new JpaStudentRepository();
            List<Student> students = repository.getAllStudents();
            for (Student student : students) {
        %>
            <tr>
                <td><%= student.getId() %></td>
                <td><%= student.getName() %></td>
                <td><%= student.getEmail() %></td>
                <td><%= student.getCoursesAsCsv() %></td>
            </tr>
        <% } %>
    </table>

<br/>
<br/>
<h2>Add a student</h2>

    <form action="addStudent.jsp">
        <div class="form-outline mb-4">
            <input type="text" name="name" value="Name..." onclick="this.value=''"/><br/>
        </div>
        <div class="form-outline mb-4">
            <input type="text" name="email"  value="Email..." onclick="this.value=''"/><br/>
        </div>

    <br/>
    <input type="submit" value="Add student" class="btn btn-primary btn-block"/>
    </form>

    <h2>Do some magic</h2>

<form action="" method="POST">
    <br/>
    <input type="submit" name="generateName" value="Generate Name" class="btn btn-primary btn-block"/>
</form>

<%
    if (request.getParameter("generateName") != null) {
        OutOfNameIdeas nameIdeas = new OutOfNameIdeas();
        Student generatedStudent = nameIdeas.getMeAName();
        String generatedName = generatedStudent.getName();
        String generatedEmail = generatedStudent.getEmail();
%>

<script>
    window.onload = function() {
        window.location.href = "addStudent.jsp?name=<%= generatedName %>&email=<%= generatedEmail %>";
    };
</script>

<%
    }
%>

</body>
</html>
