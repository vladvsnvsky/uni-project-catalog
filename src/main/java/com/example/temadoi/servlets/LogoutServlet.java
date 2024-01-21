package com.example.temadoi.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidarea sesiunii
        HttpSession session = request.getSession(false); // obține sesiunea curentă fără a crea una nouă
        if (session != null) {
            session.invalidate(); // invalidează sesiunea
        }

        // Redirecționează către pagina de login sau la pagina principală
        response.sendRedirect("login"); // ajustați calea după necesități
    }
}
