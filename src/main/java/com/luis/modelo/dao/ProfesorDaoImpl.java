package com.luis.modelo.dao;

import com.luis.modelo.BaseObject;
import com.luis.modelo.Profesor;
import java.util.List;
import com.unju.modelo.persistencia.ControladoraPersistencia;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class ProfesorDaoImpl implements ProfesorDao{
    ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();
    
    @Override
    public void crear (Profesor profesor){
        controladoraPersistencia.crear(profesor);
    }
     
    @Override
    public List<Profesor> listar (){
        Profesor pro=new Profesor();
        List<? extends BaseObject> lista = controladoraPersistencia.listar(pro);        
        Object obj= lista;
        return (List<Profesor>) obj;
    }

    @Override
    public void borrar(int idProfesor) {
        Profesor pro=new Profesor();
        pro.setId(idProfesor);
        controladoraPersistencia.borrar(pro);
    }

    @Override
    public Profesor obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Profesor t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }    
}
