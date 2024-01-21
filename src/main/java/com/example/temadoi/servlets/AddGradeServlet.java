package com.example.temadoi.servlets;

import com.example.temadoi.dao.DisciplinaDAO;
import com.example.temadoi.dao.NotaDAO;
import com.example.temadoi.models.Disciplina;
import com.example.temadoi.models.Nota;
import com.example.temadoi.models.Profesor;
import com.example.temadoi.models.Student;
import com.example.temadoi.utils.HibernateUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddGradeServlet", urlPatterns = "/addGrade")
public class AddGradeServlet extends HttpServlet {

    private NotaDAO notaDAO;
    private DisciplinaDAO disciplineDAO;

    public AddGradeServlet() {
        this.notaDAO = new NotaDAO(HibernateUtil.getSessionFactory());
        this.disciplineDAO = new DisciplinaDAO(HibernateUtil.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);


        if (session != null && "profesor".equals(session.getAttribute("userType"))) {
            Profesor profesor = (Profesor) session.getAttribute("teacher");
            String studentIdStr = request.getParameter("studentId");
            if (studentIdStr != null && !studentIdStr.isEmpty())
                {
                    int studentId = Integer.parseInt(studentIdStr);
                    request.setAttribute("studentId", studentId);
                }
            List<Disciplina> discipline = disciplineDAO.getDisciplinesByTeacherId(profesor.getId());
            request.setAttribute("discipline", discipline);

        }
        request.getRequestDispatcher("/WEB-INF/view/addGrade.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Procesează datele trimise din formular și adaugă o nouă notă
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int disciplinaId = Integer.parseInt(request.getParameter("disciplina"));
            int valoareNota = Integer.parseInt(request.getParameter("valoareNota"));

            Student student = new Student(); // Presupunem că există o modalitate de a obține un Student
            student.setId(studentId);

            Disciplina disciplina = new Disciplina(); // Presupunem că există o modalitate de a obține o Disciplină
            disciplina.setId(disciplinaId);

            Nota nota = new Nota();
            nota.setStudent(student);
            nota.setDisciplina(disciplina);
            nota.setValoare(valoareNota);
            nota.setData(new Date()); // Setează data curentă pentru notă

            notaDAO.addNota(nota);

            response.sendRedirect("dashboard");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addGrade"); // Redirecționează înapoi la formularul de adăugare notă în caz de eroare
        }
    }
}
