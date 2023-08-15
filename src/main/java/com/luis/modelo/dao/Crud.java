package com.luis.modelo.dao;

import java.util.List;

public interface Crud<T> {
    void crear(T t);
    List<T> listar();
    void borrar(int id);
    T obtener(int id); //no implementada
    boolean actualizar(T t); //no implementada
}
