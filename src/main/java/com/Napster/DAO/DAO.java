package com.Napster.DAO;

import com.Napster.MODEL.Album;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T, K>{
    void insertar(T a) throws SQLException;
    void actualizar(T a);
    void eliminar(T a);
    T obtenerid(K id);
}
