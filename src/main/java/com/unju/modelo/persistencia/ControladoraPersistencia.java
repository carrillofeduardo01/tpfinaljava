package com.unju.modelo.persistencia;

import com.luis.modelo.BaseObject;
import com.luis.modelo.Profesor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.unju.modelo.persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {
   // ProfesorJpaController profesorJpaController = new ProfesorJpaController();
    
        JpaController jpaController = new JpaController();
    
    
    
    public void crear(Profesor profesor){       
       jpaController.create(profesor);
       
    }
    
    public List<BaseObject> listar (BaseObject object){        
        return jpaController.findEntities(object); 

    }

    public void borrar(BaseObject object) {
        try {
            jpaController.destroy(object);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
