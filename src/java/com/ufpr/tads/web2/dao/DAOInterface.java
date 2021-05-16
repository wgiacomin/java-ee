package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.exceptions.DAOException;
import java.util.List;


public interface DAOInterface<T> {
    T buscar(long id) throws DAOException;
    List<T> buscarTodos() throws DAOException;
    void inserir (T t) throws DAOException;
    void remover (T t) throws DAOException;
}
