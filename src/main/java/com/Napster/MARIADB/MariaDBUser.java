package com.Napster.MARIADB;

import com.Napster.CONTROLLER.LoginController;
import com.Napster.DAO.UserDAO;
import com.Napster.MODEL.Artist;
import com.Napster.MODEL.ListsRep;
import com.Napster.MODEL.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MariaDBUser extends User implements UserDAO {
    final String INSERT = "INSERT INTO usuarios(nombre, correo, foto) VALUES(?,?,?)";
    final String UPDATE = "UPDATE usuarios SET nombre=?, correo=?, foto=? WHERE id =?";
    final String DELETE = "DELETE FROM usuarios WHERE id=?";
    final static String GETALL = "SELECT id,nombre, nacionalidad FROM artistas";
    final static String GETONE = "SELECT id, nombre, correo FROM usuarios WHERE nombre=? AND correo=?";

    private Connection con = null;

    public MariaDBUser() {
        super();
    }

    public MariaDBUser(String nombre, String correo, String foto) {
        super(nombre, correo, foto);
    }

    @Override
    public void insertar(User a) throws SQLException {
        Connection conn = null;
        conn = Conection.getConexion();

        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            PreparedStatement q = conn.prepareStatement(INSERT);
            q.setString(1, a.getNombre());
            q.setString(2, a.getCorreo());
            q.setString(3, a.getFoto());
            rs = q.executeQuery();
            q.close();
        }

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
    public static Boolean existeUsuario(User u) throws SQLException {
        Connection conn = Conection.getConexion();
        LoginController.usuarioActual=new User();
        PreparedStatement q = conn.prepareStatement(GETONE);
        q.setString(1,u.getNombre());
        q.setString(2,u.getCorreo());
        ResultSet rs=q.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            LoginController.usuarioActual.setId(id);
        }
        return LoginController.usuarioActual.getId()!=0;
    }

    @Override
    public User obtenerid(int id) {
        return null;
    }
}
