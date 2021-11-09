package com.Napster.MARIADB;

import com.Napster.DAO.GenreDAO;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Artist;
import com.Napster.MODEL.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariaDBGenre extends Genre implements GenreDAO {
    final String INSERT ="INSERT INTO generos(titulo) VALUES(?)";
    final String UPDATE ="UPDATE generos SET titulo=? WHERE id =?";
    final String DELETE ="DELETE FROM generos WHERE id=?";
    final static String  GETALL ="SELECT id,titulo FROM generos";
    final String GETONE ="SELECT titulo FROM generos WHERE id=?";

    private Connection con = null;
    public MariaDBGenre(){
        super();
    }
    public MariaDBGenre(int id, String name){
        super(id,name);
    }

    public MariaDBGenre(String name){
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

    }

    @Override
    public void eliminar(Genre a) {

    }


    public static List<Genre> listarTodos() {
        List<Genre> listado = new ArrayList<>();
        Connection conn;
        conn = Conection.getConexion();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GETALL);

            while (rs.next()) {
               int id= rs.getInt("id");
                String titulo = rs.getString("titulo");
                Genre a=new Genre(id,titulo);
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
    public Genre obtenerid(Integer id) {
        return null;
    }
}