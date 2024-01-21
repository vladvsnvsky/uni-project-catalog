package com.example.temadoi.servlets;

import com.example.temadoi.dao.NotaDAO;
import com.example.temadoi.utils.HibernateUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet(name = "DeleteGradeServlet", urlPatterns = "/deleteGrade")
public class DeleteGradeServlet extends HttpServlet {

    private NotaDAO notaDAO;

    public DeleteGradeServlet() {
        this.notaDAO = new NotaDAO(HibernateUtil.getSessionFactory()); // Inițializați corespunzător
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int notaId = Integer.parseInt(request.getParameter("notaId"));
            notaDAO.deleteNota(notaId); // Implementați metoda deleteNota în NotaDAO

            response.sendRedirect("dashboard"); // Redirecționați către pagina cu note
        } catch (Exception e) {
            // Gestionați erorile aici
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Note ID");
        }
    }
}

