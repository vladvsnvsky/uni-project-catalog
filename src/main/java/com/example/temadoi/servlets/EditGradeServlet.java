package com.example.temadoi.servlets;

import com.example.temadoi.dao.NotaDAO;
import com.example.temadoi.models.Nota;
import com.example.temadoi.utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
// ... alte importuri necesare ...

@WebServlet(name = "EditGradeServlet", urlPatterns = "/editGrade")
public class EditGradeServlet extends HttpServlet {

    private NotaDAO notaDAO;

    public EditGradeServlet() {
        this.notaDAO = new NotaDAO(HibernateUtil.getSessionFactory()); // Inițializați corespunzător
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && "profesor".equals(session.getAttribute("userType"))) {
            // Logica pentru afișarea formularului de editare notă
            int notaId = Integer.parseInt(request.getParameter("notaId"));
            Nota nota = notaDAO.getNotaById(notaId); // Presupunând că aveți această metodă în NotaDAO

            if (nota != null) {
                request.setAttribute("nota", nota);
                request.getRequestDispatcher("/WEB-INF/view/editGrade.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Nota nu a fost găsită");
            }
        } else {
            response.sendRedirect("login"); // Redirecționați către login dacă utilizatorul nu este profesor
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logica pentru actualizarea notei
        HttpSession session = request.getSession(false);
        if (session != null && "profesor".equals(session.getAttribute("userType"))) {
            try {
                int notaId = Integer.parseInt(request.getParameter("notaId"));
                int valoareNota = Integer.parseInt(request.getParameter("valoareNota"));
                // Alte parametri necesari

                Nota nota = notaDAO.getNotaById(notaId);
                if (nota != null) {
                    nota.setValoare(valoareNota);
                    // Setează alte atribute ale notei, dacă este necesar

                    notaDAO.updateNota(nota); // Actualizează nota

                    response.sendRedirect("dashboard"); // Redirecționează către lista de note
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Nota nu a fost găsită");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("editGrade?notaId=" + request.getParameter("notaId")); // Redirecționează înapoi la formular în caz de eroare
            }
        } else {
            response.sendRedirect("login"); // Redirecționați către login dacă utilizatorul nu este profesor
        }
    }
}
