package com.Napster.MARIADB;

import com.Napster.DAO.ListRepDAO;
import com.Napster.MODEL.ListsRep;

import java.sql.Connection;
import java.util.List;

public class MariaDBListRep extends ListsRep implements ListRepDAO {
    final String INSERT ="INSERT INTO artistas(nombre, nacionalidad, foto) VALUES(?,?,?,?)";
    final String UPDATE ="UPDATE artistas SET nombre=?, nacionalidad=?, foto=? WHERE id =?";
    final String DELETE ="DELETE FROM artistas WHERE id=?";
    final static String GETALL ="SELECT id,nombre, nacionalidad FROM artistas";
    final String GETONE ="SELECT nombre, nacionalidad FROM generos WHERE id=?";

    private Connection con = null;

    public MariaDBListRep(String nombre, String descripcion, String creador){
        super(nombre, descripcion, creador);
    }
    @Override
    public void insertar(ListsRep a) {

    }

    @Override
    public void actualizar(ListsRep a) {

    }

    @Override
    public void eliminar(ListsRep a) {

    }


    public static List<ListsRep> listarTodos() {
        return null;
    }

    @Override
    public ListsRep obtenerid(Integer id) {
        return null;
    }
}
