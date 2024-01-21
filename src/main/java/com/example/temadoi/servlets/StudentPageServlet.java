package com.example.temadoi.servlets;

import com.example.temadoi.dao.DisciplinaDAO;
import com.example.temadoi.dao.NotaDAO;
import com.example.temadoi.dao.StudentDAO;
import com.example.temadoi.models.Disciplina;
import com.example.temadoi.models.Nota;
import com.example.temadoi.models.Student;
import com.example.temadoi.models.Medie;
import com.example.temadoi.utils.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentPageServlet", urlPatterns = "/studentPage")
public class StudentPageServlet extends HttpServlet {
    private DisciplinaDAO disciplinaDAO;
    private StudentDAO studentDAO;
    private NotaDAO notaDAO;


    public StudentPageServlet() {

        this.disciplinaDAO = new DisciplinaDAO(HibernateUtil.getSessionFactory()); // Inițializați corespunzător
        this.notaDAO = new NotaDAO(HibernateUtil.getSessionFactory());
        this.studentDAO = new StudentDAO(HibernateUtil.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && "profesor".equals(session.getAttribute("userType"))) {
            // Presupunem că obiectul "student" este deja setat în sesiune
            int studentId = Integer.parseInt(request.getParameter("studentId"));

            // Obțineți mediile studentului de la medieDAO
            List<Medie> medii = new ArrayList<>();

            Student student = studentDAO.getStudentById(studentId);

            List<Disciplina> discipline = disciplinaDAO.getAllDiscipline();
            for(Disciplina d : discipline){
                List<Nota> note = notaDAO.getNoteByStudentAndDisciplina(student.getId(), d.getId());
                Medie mediaD = new Medie(d, note);
                medii.add(mediaD);
            }

            request.setAttribute("student", student);
            request.setAttribute("medii", medii);
            request.getRequestDispatcher("/WEB-INF/view/studentPage.jsp").forward(request, response);
        } else {
            // Redirecționați către pagina de login sau altă pagină adecvată
            response.sendRedirect("login");
        }
    }
}
