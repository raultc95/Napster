package com.Napster.MARIADB;

import com.Napster.DAO.GenreDAO;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Artist;
import com.Napster.MODEL.Genre;
import com.Napster.MODEL.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariaDBGenre extends Genre implements GenreDAO {
    final String INSERT = "INSERT INTO generos(titulo) VALUES(?)";
    final String UPDATE = "UPDATE generos SET titulo=? WHERE id =?";
    final static String GETALL = "SELECT id,titulo FROM generos";
    final String GETONE = "SELECT id, titulo FROM generos WHERE id=?";

    private Connection con = null;

    public MariaDBGenre() {
        super();
    }

    public MariaDBGenre(int id, String name) {
        super(id, name);
    }

    public MariaDBGenre(String name) {
        super(name);
    }

    public MariaDBGenre(int id) {
        super(id);
    }

    @Override
    public void insertar(Genre a) throws SQLException {
        Connection conn = null;
        conn = Conection.getConexion();

        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            PreparedStatement q = conn.prepareStatement(INSERT);
            q.setString(1, this.name);
            rs = q.executeQuery();
            Genre g = new Genre();
            q.close();
        }

    }

    @Override
    public void actualizar(Genre a) {
        Connection conn = null;
        conn = Conection.getConexion();
        if (conn != null) {
            try {
                PreparedStatement q = conn.prepareStatement(UPDATE);
                q.setString(1, a.getName());
                q.setInt(2, a.getId());
                q.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Genre a) {

    }

    /**
     * Con esto puedo listar y ya apareceria en el comboBox
     * @return
     */
    public static List<Genre> listarTodos() {
        List<Genre> listado = new ArrayList<>();
        Connection conn;
        conn = Conection.getConexion();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GETALL);

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                Genre a = new Genre(id, titulo);
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
    public Genre obtenerid(int id) {
        Genre result = null;
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
                    result = new Genre();
                    result.setId(rs.getInt("id"));
                    result.setName(rs.getString("titulo"));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
