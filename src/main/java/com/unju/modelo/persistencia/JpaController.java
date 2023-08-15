/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unju.modelo.persistencia;

import com.luis.modelo.BaseObject;
import com.luis.modelo.Profesor;
import com.unju.modelo.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author eduardo
 */
public class JpaController implements Serializable{
    
    public JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public JpaController() {
        //referencia al archivo persistence.xml y a la unidad de persistencia llamada crudPU
        emf = Persistence.createEntityManagerFactory("crudPU");
    }
    public void create(BaseObject object) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BaseObject object) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            object = em.merge(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = object.getId();
                if (find(object) == null) {
                    throw new NonexistentEntityException("The object with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BaseObject object) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BaseObject obj;
            try {
                obj = em.getReference(object.getClass(), object.getId());
                obj.getClass();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The object with id " + object.getId() + " no longer exists.", enfe);
            }
            em.remove(obj);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BaseObject> findEntities(BaseObject obj) {
        return findProfesorEntities(true, -1, -1, obj);
    }

    public List<BaseObject> findProfesorEntities(int maxResults, int firstResult,BaseObject obj) {
        return findProfesorEntities(false, maxResults, firstResult, obj);
    }

    private List<BaseObject> findProfesorEntities(boolean all, int maxResults, int firstResult,BaseObject obj) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(obj.getClass()));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BaseObject find(BaseObject object) {
        EntityManager em = getEntityManager();
        try {
            return em.find(object.getClass(), object.getId());
        } finally {
            em.close();
        }
    }

    public int getCount(BaseObject obj) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaseObject> rt = cq.from(obj.getClass());
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
