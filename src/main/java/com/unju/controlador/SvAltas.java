package com.unju.controlador;

import com.luis.modelo.dao.ProfesorDaoImpl;
import com.luis.modelo.Profesor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luis
 */
@WebServlet(name = "SvAltas", urlPatterns = {"/SvAltas"})
public class SvAltas extends HttpServlet {
    
    ProfesorDaoImpl profesorDaoImpl = new ProfesorDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Listar profesores
        List<Profesor> profesores = new ArrayList<>();
        
        profesores = profesorDaoImpl.listar();
        
        HttpSession session = request.getSession();
        session.setAttribute("profesores", profesores);
        
        response.sendRedirect("mostrarProfesores.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Alta de profesores
        String legajo = request.getParameter("legajo");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        
        Profesor profesor = new Profesor();
        
        profesor.setLegajo(legajo);
        profesor.setNombres(nombres);
        profesor.setApellidos(apellidos);
        profesor.setTelefono(telefono);
        
        profesorDaoImpl.crear(profesor);
        
        response.sendRedirect("index.html");
    }
}
