package com.example.temadoi.servlets;

import com.example.temadoi.dao.StudentDAO;
import com.example.temadoi.models.Student;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StudentProfileServlet extends HttpServlet {

    private StudentDAO studentDAO;

    public StudentProfileServlet() {
        // Aici presupunem că StudentDAO este inițializat într-un mod adecvat
        // De exemplu, prin Dependency Injection sau printr-o instanță singleton
        this.studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("studentId") != null) {
            int studentId = (int) session.getAttribute("studentId");
            Student student = studentDAO.getStudentById(studentId);

            if (student != null) {
                request.setAttribute("student", student);
                request.getRequestDispatcher("/WEB-INF/view/studentProfile.jsp").forward(request, response);
            } else {
                response.sendRedirect("login"); // Redirect to login if student not found
            }
        } else {
            response.sendRedirect("login"); // Redirect to login if not authenticated
        }
    }

    // Metoda doPost poate fi adăugată pentru a gestiona actualizările profilului
}
