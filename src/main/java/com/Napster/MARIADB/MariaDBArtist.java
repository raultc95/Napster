package com.Napster.MARIADB;

import com.Napster.DAO.ArtistDAO;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Artist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariaDBArtist extends Artist implements ArtistDAO {

    final String INSERT = "INSERT INTO artistas(id,nombre, nacionalidad, foto) VALUES(?,?,?,?)";
    final String UPDATE = "UPDATE artistas SET  nombre=?, nacionalidad=?, foto=? WHERE id =?";
    final String DELETE = "DELETE FROM artistas WHERE id=?";
    final static String GETALL = "SELECT id,nombre,nacionalidad FROM artistas";
    final static String GETONE = "SELECT id,nombre, nacionalidad FROM artistas WHERE id=?";

    private Connection con = null;

    public MariaDBArtist() {
        super();
    }
    public MariaDBArtist(int id,String nombre){
        super(id,nombre);
    }

    public MariaDBArtist(String nombre, String nacionalidad, String foto) {
        super(nombre, nacionalidad, foto);
    }

    public MariaDBArtist(int id) {
        super(id);
        Artist a = obtenerid(id);
        setId(a.getId());
        setNombre(a.getNombre());
        setNacionalidad(a.getNacionalidad());
        setFoto(a.getFoto());
    }

    @Override
    public void insertar(Artist a) throws SQLException {

        Connection conn = null;
        conn = Conection.getConexion();

        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            PreparedStatement q = conn.prepareStatement(INSERT);
            q.setInt(1, this.id);
            q.setString(2, this.nombre);
            q.setString(3, this.nacionalidad);
            q.setString(4, this.foto);
            rs = q.executeQuery();
            Artist art = new Artist();
            q.close();
        }

    }

    @Override
    public void actualizar(Artist a) {
        Connection conn = null;
        conn = Conection.getConexion();
        if(conn!=null){
            try {
                PreparedStatement q = conn.prepareStatement(UPDATE);
                q.setString(1,a.getNombre());
                q.setString(2,a.getNacionalidad());
                q.setString(3,getFoto());
                q.setInt(4,a.getId());
                q.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void eliminar(Artist a)throws SQLException {
        Connection conn = null;
        conn = Conection.getConexion();
        if(conn!=null){
            PreparedStatement q = null;
            try {
                q = conn.prepareStatement(DELETE);
                q.setInt(1,a.getId());
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
    public static List<Artist> listarTodos() {
        List<Artist> listado = new ArrayList<>();
        Connection conn;
        conn = Conection.getConexion();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GETALL);

            while (rs.next()) {
                int id= rs.getInt("id");
                String nombre = rs.getString("nombre");
                String nacionalidad= rs.getString("nacionalidad");
                Artist a=new Artist(id,nombre, nacionalidad);
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
    public Artist obtenerid(int id) {
        Artist result = new Artist();
        Connection conn;
        conn = Conection.getConexion();
        try {
            PreparedStatement q = conn.prepareStatement(GETONE);
            q.setInt(1, id);
            ResultSet rs = q.executeQuery();

            while (rs.next()) {
                id= rs.getInt("id");
                String nombre = rs.getString("nombre");
                String nacionalidad = rs.getString("nacionalidad");
                result.setId(id);
                result.setNombre(nombre);
                result.setNacionalidad(nacionalidad);

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
