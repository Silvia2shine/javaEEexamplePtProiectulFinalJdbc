package com.siit.studentRepoExample;
import com.siit.studentRepoExample.model.Student;
import com.siit.studentRepoExample.repository.JdbcStudentRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class GetStudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JdbcStudentRepository repository = new JdbcStudentRepository();
        List<Student> students = repository.getAllStudents();

        resp.setContentType("text/html");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head><title>Student List From DO GET method</title></head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h1>Student List From DO GET method</h1>");
        resp.getWriter().println("<table border=\"1\">");
        resp.getWriter().println("<tr><th>ID</th><th>Name</th><th>Email</th></tr>");

        for (Student student : students) {
            resp.getWriter().println("<tr>");
            resp.getWriter().println("<td>" + student.getId() + "</td>");
            resp.getWriter().println("<td>" + student.getName() + "</td>");
            resp.getWriter().println("<td>" + student.getEmail() + "</td>");
            resp.getWriter().println("</tr>");
        }

        resp.getWriter().println("</table>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");
    }


}