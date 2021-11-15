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
    final String INSERT = "INSERT INTO listas_de_reproduccion(id, nombre, descripcion,id_creador) VALUES(?,?,?,?,?)";
    final String UPDATE = "UPDATE listas_de_reproduccion SET nombre=?, descripcion=?, id_creador=? WHERE id =?";
    final String DELETE = "DELETE FROM listas_de_reproduccion WHERE id=?";
    final static String GETALL = "SELECT id,nombre, descripcion,id_creador FROM listas_de_reproduccion";
    final static String GETONE = "SELECT id,nombre FROM listas_de_reproduccion WHERE id=?";
    final static String GETLISTUSER = "SELECT  id, nombre FROM listas_de_reproduccion WHERE id_creador=?";
    final static String LISTADO="SELECT id_cancion FROM cancion_play WHERE id_play=?";
    /*final static String LISTADO = "SELECT canciones.titulo,artistas.nombre,discos.nombre AS album ,generos.titulo AS genero\n" +
            "FROM canciones,artistas,listas_de_reproduccion,cancion_play,discos,generos\n" +
            "WHERE canciones.id=cancion_play.id_cancion AND listas_de_reproduccion.id=cancion_play.id_play\n" +
            "AND listas_de_reproduccion.id=? AND canciones.id_disco=discos.id AND discos.id_artista=artistas.id\n" +
            "AND canciones.id_genero=generos.id";*/

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
