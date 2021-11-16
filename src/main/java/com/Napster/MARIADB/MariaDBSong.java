package com.Napster.MARIADB;

import com.Napster.DAO.SongDAO;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Artist;
import com.Napster.MODEL.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariaDBSong extends Song implements SongDAO {
    final String INSERT = "INSERT INTO canciones(titulo, duracion,id_genero,id_disco) VALUES(?,?,?,?)";
    final String GETDISC = "SELECT id,nombre,fecha_publicacion,foto,id_artista from discos WHERE id=?";
    final String UPDATE = "UPDATE canciones SET titulo=?, duracion=?, id_genero=?,id_disco=? WHERE id =?";
    final String DELETE = "DELETE FROM canciones WHERE id=?";
    final static String GETALL = "SELECT id,titulo FROM canciones";
    final String GETONE = "SELECT id,titulo,duracion,id_genero,n_reproducciones,id_disco FROM canciones WHERE id=?";
    final static String GETARTDISC = "SELECT id,titulo,id_disco FROM canciones";

    private Connection con = null;
    private Object Album;
    private Object Genre;

    public MariaDBSong() {

    }

    public MariaDBSong(int id) {
        super(id);
    }

    public MariaDBSong(String nombre) {
        super(nombre);
    }

    public MariaDBSong(String nombre, int duracion, int nreproducciones) {
        super(nombre, duracion, nreproducciones);
    }

    public MariaDBSong(int id, String nombre) {
        super(id, nombre);

    }

    public MariaDBSong(int id, String nombre, int id_disco) {
    }

    @Override
    public void insertar(Song a) throws SQLException {
        Connection conn = null;
        conn = Conection.getConexion();

        if (conn != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            PreparedStatement q = null;
            try {
                q = conn.prepareStatement(INSERT);
                //q.setInt(1,this.id);
                q.setString(1, this.nombre);
                q.setInt(2, this.duracion);
                q.setInt(3, this.getGenre().getId());
                q.setInt(4, this.getAlbum().getId());


                rs = q.executeQuery();
                q.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void actualizar(Song a) {
        Connection conn = null;
        conn = Conection.getConexion();
        if (conn != null) {
            try {
                PreparedStatement q = conn.prepareStatement(UPDATE);
                q.setString(1, a.getNombre());
                q.setInt(2, a.getDuracion());
                //q.setInt(4,a.getN_reproducciones());
                q.setInt(3, a.getGenre().getId());
                q.setInt(4, a.getAlbum().getId());
                q.setInt(5, a.getId());
                q.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void eliminar(Song a) throws SQLException {
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

    @Override
    public Song obtenerid(int id) {
        Song result = null;
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
                    result = new Song();
                    result.setId(rs.getInt("id"));
                    result.setNombre(rs.getString("titulo"));
                    result.setAlbum(new MariaDBAlbum().obtenerid(rs.getInt("id_disco")));
                    result.setGenre(new MariaDBGenre().obtenerid(rs.getInt("id_genero")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }




        public static List<Song> listarTodos() {
            List<Song> listado = new ArrayList<>();
            Connection conn = Conection.getConexion();

            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(GETALL);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("titulo");
                    MariaDBSong a = new MariaDBSong(id, nombre);
                    listado.add(a);

                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                listado = new ArrayList<>();
            }
            return listado;
        }


  /*  @Override
    public Song obtenerid(Integer id) {
        Song result = null;
        Connection conn;
        conn = Conection.getConexion();
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(GETONE);
            if (rs != null) {
                while (rs.next()) {
                    result = new Song();
                    result.setId(rs.getInt("id"));
                    result.setNombre(rs.getString("titulo"));
                    result.setDuracion(rs.getInt("duracion"));
                    //result.setGenero(rs.);
                    result.setNreproducciones(rs.getInt("n_reproducciones"));
                    int id_disc = rs.getInt("id_disco");
                    PreparedStatement ps = conn.prepareStatement(GETDISC);
                    ps.setInt(1, id_disc);
                    ResultSet rs1 = ps.executeQuery();
                    if (rs1 != null) {
                        while (rs1.next()) {
                            Album a = new Album();
                            a.setId(rs1.getInt("id"));
                            a.setNombre(rs1.getString("nombre"));
                            a.setFecha_publicacion(rs1.getInt("fecha_publicacion"));
                            a.setFoto(rs1.getString("foto"));
                            result.setAlbum(a);
                            int id_artista = rs.getInt("id_artista");
                            PreparedStatement ps1 = conn.prepareStatement(GETARTDISC);
                            ps1.setInt(1, id_artista);
                            ResultSet rs2 = ps1.executeQuery();
                            if (rs2 != null) {
                                while (rs2.next()) {
                                    Artist art = new Artist();
                                    art.setNombre(rs2.getString("nombre"));
                                    result.getAlbum();
                                }
                            }
                        }
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }*/

}

