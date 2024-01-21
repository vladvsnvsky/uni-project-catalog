package com.example.temadoi.servlets;

import com.example.temadoi.dao.ProfesorDAO;
import com.example.temadoi.dao.StudentDAO;
import com.example.temadoi.models.Profesor;
import com.example.temadoi.models.Student;
import com.example.temadoi.utils.HibernateUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    private StudentDAO studentDAO;
    private ProfesorDAO profesorDAO;
    public LoginServlet(){
        this.studentDAO = new StudentDAO(HibernateUtil.getSessionFactory());
        this.profesorDAO = new ProfesorDAO(HibernateUtil.getSessionFactory());
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Logica pentru verificarea credențialelor
        Student student = studentDAO.findByUsernameAndPassword(username, password);
        Profesor profesor = null;
        if (student == null) {
            profesor = profesorDAO.findByUsernameAndPassword(username, password);
        }

        if (student != null || profesor != null) {
            // Setează utilizatorul ca autentificat în sesiune
            HttpSession session = request.getSession();
            if (student != null) {
                session.setAttribute("student", student);
                session.setAttribute("userType", "student");
            } else {
                session.setAttribute("teacher", profesor);
                session.setAttribute("userType", "profesor");
            }

            // Redirect to the appropriate dashboard based on user type
            if ("student".equals(session.getAttribute("userType"))) {
                response.sendRedirect("http://localhost:8080/temadoi_war_exploded/dashboard"); // Redirect to student dashboard
            } else {
                response.sendRedirect("http://localhost:8080/temadoi_war_exploded/dashboard"); // Redirect to profesor dashboard
            }
        } else {
            // Redirecționează înapoi la pagina de login cu un mesaj de eroare
            request.setAttribute("errorMessage", "Nume de utilizator sau parolă incorectă");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }
}

