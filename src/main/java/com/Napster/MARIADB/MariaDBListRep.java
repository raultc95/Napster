package com.Napster.MARIADB;

import com.Napster.DAO.ListRepDAO;
import com.Napster.MODEL.Genre;
import com.Napster.MODEL.ListsRep;
import com.Napster.MODEL.Song;
import com.Napster.MODEL.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MariaDBListRep extends ListsRep implements ListRepDAO {
    final static String GETALL = "SELECT id,nombre, descripcion,id_creador FROM listas_de_reproduccion";
    final static String GETLISTUSER = "SELECT  id, nombre FROM listas_de_reproduccion WHERE id_creador=?";
    final static String LISTADO="SELECT id_cancion FROM cancion_play WHERE id_play=?";


    private Connection con = null;

    public MariaDBListRep(String nombre, String descripcion, String creador) {
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
        List<ListsRep> listado = new ArrayList<>();
        Connection conn;
        conn = Conection.getConexion();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GETALL);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                ListsRep a = new ListsRep(id, nombre);
                listado.add(a);


            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listado = new ArrayList<>();
        }
        return listado;
    }

    public static List<Song> listarCanciones(ListsRep list) {
        List<Song> listado = new ArrayList<>();
        Connection conn;
        conn = Conection.getConexion();

        try {
            PreparedStatement q = conn.prepareStatement(LISTADO);
            q.setInt(1, list.getId());
            ResultSet rs = q.executeQuery();

            while (rs.next()) {
                Song s = new MariaDBSong().obtenerid(rs.getInt("id_cancion"));
                listado.add(s);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listado = new ArrayList<>();
        }
        return listado;
    }

    public static List<ListsRep> listarPorUsuario(User user) {
        List<ListsRep> listado = new ArrayList<>();
        Connection conn = Conection.getConexion();

        try {
            PreparedStatement q = conn.prepareStatement(GETLISTUSER);
            q.setInt(1, user.getId());
            ResultSet rs = q.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                ListsRep a = new ListsRep(id, nombre);
                listado.add(a);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            listado = new ArrayList<>();
        }
        return listado;
    }


    @Override
    public ListsRep obtenerid(int id) {
        return null;
    }
}
