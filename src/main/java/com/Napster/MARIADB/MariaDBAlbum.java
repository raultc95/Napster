package com.Napster.MARIADB;

import com.Napster.DAO.AlbumDAO;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Artist;
import com.Napster.MODEL.Song;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MariaDBAlbum extends Album implements AlbumDAO {
    final String INSERT = "INSERT INTO discos(nombre, fecha_publicacion,foto,id_artista) VALUES(?,?,?,?)";
    final String UPDATE = "UPDATE discos SET nombre=?, fecha_publicacion=?, foto=?,id_artista=? WHERE id =?";
    final String DELETE = "DELETE FROM discos WHERE id=?";
    final static String GETALL = "SELECT id,nombre FROM discos";
    final String GETONE = "SELECT id, nombre, id_artista FROM discos WHERE id=?";

    private Connection con = null;

    public MariaDBAlbum() {
        super();
    }

    public MariaDBAlbum(String nombre) {
        super(nombre);
    }

    public MariaDBAlbum(String nombre, LocalDate fecha_publicacion, String foto) {
        super(nombre, fecha_publicacion, foto);
    }

    public MariaDBAlbum(int id) {
        super();
    }


    @Override
    public void insertar(Album a) throws SQLException {
        Connection conn = null;
        conn = Conection.getConexion();

        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                PreparedStatement q = conn.prepareStatement(INSERT);
                q.setString(1, this.nombre);
                q.setDate(2, Date.valueOf(this.fecha_publicacion));
                q.setString(3, this.foto);
                q.setInt(4, this.getArtist().getId());
                rs = q.executeQuery();
                q.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void actualizar(Album a) {
        Connection conn = null;
        conn = Conection.getConexion();
        if (conn != null) {
            try {
                PreparedStatement q = conn.prepareStatement(UPDATE);
                q.setString(1, a.getNombre());
                q.setDate(2, Date.valueOf(a.getFecha_publicacion()));
                q.setString(3, getFoto());
                q.setInt(4, a.getArtist().getId());
                q.setInt(5, a.getId());
                q.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void eliminar(Album a) throws SQLException {
        Connection conn = null;
        conn = Conection.getConexion();
        if (conn != null) {
            PreparedStatement q = null;
            try {
                q = conn.prepareStatement(DELETE);
                q.setInt(1, a.getId());
                q.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Con esto puedo listar y ya apareceria en el comboBox
     * @return
     */
    public static List<Album> listarTodos() {
        List<Album> listado = new ArrayList<>();
        Connection conn;
        conn = Conection.getConexion();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GETALL);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Album a = new Album(id, nombre);
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
    public Album obtenerid(int id) {
        Album result = null;
        Connection conn = Conection.getConexion();
        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            PreparedStatement q = null;
            try {
                q = conn.prepareStatement(GETONE);
                q.setInt(1, id);
                rs = q.executeQuery();
                while (rs.next()) {
                    result = new Album();
                    result.setId(rs.getInt("id"));
                    result.setNombre(rs.getString("nombre"));
                    //result.setAlbum(new MariaDBAlbum().obtenerid(rs.getInt("id_disco")));
                    //result.setGenre(new MariaDBGenre().obtenerid(rs.getInt("id_genero")));
                    result.setArtist(new MariaDBArtist().obtenerid(rs.getInt("id_artista")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
