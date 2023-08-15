package com.unju.controlador;

import com.luis.modelo.dao.ProfesorDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis
 */
@WebServlet(name = "SvBajas", urlPatterns = {"/SvBajas"})
public class SvBajas extends HttpServlet {
    
    ProfesorDaoImpl profesorDaoImpl = new ProfesorDaoImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Baja Profesor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Profesor dado de baja.. " + request.getContextPath() + "</h1>");
            out.println("<a href='index.html'>Volver al inicio</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProfesor = Integer.parseInt(request.getParameter("id_profesor"));
        profesorDaoImpl.borrar(idProfesor);
        processRequest(request, response);
    }
}
