package com.Napster.MARIADB;

import com.Napster.DAO.UserDAO;
import com.Napster.MODEL.User;

import java.sql.Connection;
import java.util.List;

public class MariaDBUser extends User implements UserDAO {
    final String INSERT ="INSERT INTO artistas(nombre, nacionalidad, foto) VALUES(?,?,?,?)";
    final String UPDATE ="UPDATE artistas SET nombre=?, nacionalidad=?, foto=? WHERE id =?";
    final String DELETE ="DELETE FROM artistas WHERE id=?";
    final static String GETALL ="SELECT id,nombre, nacionalidad FROM artistas";
    final String GETONE ="SELECT nombre, nacionalidad FROM generos WHERE id=?";

    private Connection con = null;

    public MariaDBUser(String nombre, String correo, String foto){
        super(nombre, correo, foto);
    }
    @Override
    public void insertar(User a) {

    }

    @Override
    public void actualizar(User a) {

    }

    @Override
    public void eliminar(User a) {

    }


    public static List<User> listarTodos() {
        return null;
    }

    @Override
    public User obtenerid(Integer id) {
        return null;
    }
}
