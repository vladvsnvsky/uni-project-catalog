package com.example.temadoi.servlets;

import com.example.temadoi.dao.DisciplinaDAO;
import com.example.temadoi.dao.NotaDAO;
import com.example.temadoi.dao.StudentDAO;
import com.example.temadoi.models.Disciplina;
import com.example.temadoi.models.Nota;
import com.example.temadoi.models.Student;
import com.example.temadoi.utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewStudentsServlet", urlPatterns = "/students")
public class ViewStudentsServlet extends HttpServlet {

    private StudentDAO studentDAO;
    private NotaDAO notaDAO;
    private DisciplinaDAO disciplinaDAO;

    public ViewStudentsServlet() {
        this.disciplinaDAO = new DisciplinaDAO(HibernateUtil.getSessionFactory());
        this.notaDAO = new NotaDAO(HibernateUtil.getSessionFactory());
        this.studentDAO = new StudentDAO(HibernateUtil.getSessionFactory()); // Presupunem existența unui constructor implicit sau parametrizat corespunzător
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        List<Student> students = studentDAO.getAllStudents(); // Presupunem existența metodei getAllStudents()
//        request.setAttribute("students", students);
//        request.getRequestDispatcher("/WEB-INF/view/viewStudents.jsp").forward(request, response);

        HttpSession session = request.getSession(false);

        // Verifică dacă sesiunea există și dacă tipul de utilizator este "profesor"
        if (session != null && "profesor".equals(session.getAttribute("userType"))) {
            List<Student> students = studentDAO.getAllStudents();

            request.setAttribute("students", students);
            request.getRequestDispatcher("/WEB-INF/view/viewStudents.jsp").forward(request, response);
        } else {
            // Redirecționează către "dashboard" dacă utilizatorul nu este profesor sau nu este autentificat
            response.sendRedirect("dashboard");
        }
    }
}
