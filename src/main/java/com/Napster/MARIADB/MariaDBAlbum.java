package com.Napster.MARIADB;
import com.Napster.DAO.AlbumDAO;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Artist;
import com.Napster.MODEL.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariaDBAlbum extends Album implements AlbumDAO {
    final String INSERT ="INSERT INTO discos(nombre, fecha_publicacion,foto,id_artista) VALUES(?,?,?,?)";
    final String UPDATE ="UPDATE discos SET nombre=?, fecha_publicacion=?, foto=?,id_artistas=? WHERE id =?";
    final String DELETE ="DELETE FROM artistas WHERE id=?";
    final static String GETALL ="SELECT id,nombre FROM discos";
    final String GETONE ="SELECT nombre, nacionalidad FROM generos WHERE id=?";

    private Connection con = null;
    public MariaDBAlbum(){
        super();
    }
    public MariaDBAlbum(String nombre){
        super(nombre);
    }
    public MariaDBAlbum(String nombre,int fecha_publicacion,String foto){
        super(nombre,fecha_publicacion,foto);
    }

    public MariaDBAlbum(int id) {
        super();
    }


    @Override
    public void insertar(Album a) throws SQLException {
        Connection conn = null;
        conn = Conection.getConexion();

        if (conn != null) {
            PreparedStatement ps=null;
            ResultSet rs=null;
            try {
                PreparedStatement q = conn.prepareStatement(INSERT);
                q.setString(1, this.nombre);
                q.setInt(2, this.fecha_publicacion);
                q.setString(3, this.foto);
                q.setInt(4,this.getArtist().getId());
                rs =q.executeQuery();
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
        if(conn!=null){
            try {
                PreparedStatement q = conn.prepareStatement(UPDATE);
                q.setString(1,a.getNombre());
                q.setInt(2,a.getFecha_publicacion());
                q.setString(3,getFoto());
                //q.setInt(4,a.getN_reproducciones());
                q.setInt(4,a.getArtist().getId());
                q.setInt(5,a.getId());
                q.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void eliminar(Album a) {

    }


    public static List<Album> listarTodos() {
        List<Album> listado = new ArrayList<>();
        Connection conn;
        conn = Conection.getConexion();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GETALL);

            while (rs.next()) {
                int id= rs.getInt("id");
                String nombre = rs.getString("nombre");
                Album a=new Album(id,nombre);
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
    public Album obtenerid(Integer id) {
        return null;
    }
}
