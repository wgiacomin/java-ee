package com.ufpr.tads.web2.dao.utils;

import com.ufpr.tads.web2.exceptions.DAOException;
import java.util.List;


public interface DAOInterface<T> {
    public T buscar(T t) throws DAOException;
    public List<T> buscarTodos() throws DAOException;
    public void inserir (T t) throws DAOException;
    public void remover (T t) throws DAOException;
}
