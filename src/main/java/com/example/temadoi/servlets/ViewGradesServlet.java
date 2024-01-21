package com.example.temadoi.servlets;

import com.example.temadoi.dao.DisciplinaDAO;
import com.example.temadoi.dao.NotaDAO;
import com.example.temadoi.models.Disciplina;
import com.example.temadoi.models.Nota;
import com.example.temadoi.models.Profesor;
import com.example.temadoi.models.Student;
import com.example.temadoi.utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "dashboard", value = "/dashboard")
public class ViewGradesServlet extends HttpServlet {

    private NotaDAO notaDAO;
    private DisciplinaDAO disciplinaDAO;

    public ViewGradesServlet() {
        this.disciplinaDAO = new DisciplinaDAO(HibernateUtil.getSessionFactory());
        this.notaDAO = new NotaDAO(HibernateUtil.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Student student = null;
            if(session.getAttribute("student") != null)
            {
                student = (Student) session.getAttribute("student");
                List<Nota> note = notaDAO.getNoteByStudentId(student.getId());
                request.setAttribute("note", note);
            }
            Profesor profesor = null;
            if(session.getAttribute("teacher") != null)
            {
                profesor = (Profesor) session.getAttribute("teacher");
                List<Disciplina> disciplines = disciplinaDAO.getDisciplinesByTeacherId(profesor.getId());
                List<Nota> note = new ArrayList<>();
                for(Disciplina d : disciplines){
                    List<Nota> nd = notaDAO.getNoteByDisciplineId(d.getId());
                    for(Nota n : nd){
                        note.add(n);
                    }
                }
                request.setAttribute("note", note);
            }


            request.getRequestDispatcher("/WEB-INF/view/viewGrade.jsp").forward(request, response);
        } else {
            response.sendRedirect("login"); // Redirect to login if the user is not authenticated
        }
    }
}
