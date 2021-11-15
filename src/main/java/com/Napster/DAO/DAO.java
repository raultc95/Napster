package com.Napster.DAO;

import com.Napster.MODEL.Album;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
    void insertar(T a) throws SQLException;
    void actualizar(T a);
    void eliminar(T a) throws SQLException;
    T obtenerid(int id);
}
