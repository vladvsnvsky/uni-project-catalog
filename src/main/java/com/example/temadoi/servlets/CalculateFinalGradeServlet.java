package com.example.temadoi.servlets;
import com.example.temadoi.dao.NotaDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.example.temadoi.models.Nota;

import java.util.List;

public class CalculateFinalGradeServlet extends HttpServlet {

    private NotaDAO notaDAO;

    public CalculateFinalGradeServlet() {
        this.notaDAO = new NotaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int disciplinaId = Integer.parseInt(request.getParameter("disciplinaId"));

            List<Nota> note = notaDAO.getNoteByStudentAndDisciplina(studentId, disciplinaId);
            double medieFinala = calculateAverage(note);

            // Logica pentru a salva sau a trimite media finală unde este necesar
            // De exemplu, setarea ca atribut pentru a fi afișată într-o pagină JSP
            request.setAttribute("medieFinala", medieFinala);
            request.getRequestDispatcher("/WEB-INF/view/showAverage.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("gradebook");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("gradebook");
        }
    }

    private double calculateAverage(List<Nota> note) {
        if (note == null || note.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Nota nota : note) {
            sum += nota.getValoare();
        }
        return sum / note.size();
    }
}

