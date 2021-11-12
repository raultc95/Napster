package com.Napster.MARIADB;

import com.Napster.DAO.ListRepDAO;
import com.Napster.MODEL.Genre;
import com.Napster.MODEL.ListsRep;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MariaDBListRep extends ListsRep implements ListRepDAO {
    final String INSERT = "INSERT INTO listas_de_reproduccion(id, nombre, descripcion,id_creador) VALUES(?,?,?,?,?)";
    final String UPDATE = "UPDATE listas_de_reproduccion SET nombre=?, descripcion=?, id_creador=? WHERE id =?";
    final String DELETE = "DELETE FROM listas_de_reproduccion WHERE id=?";
    final static String GETALL = "SELECT id,nombre, descripcion,id_creador FROM listas_de_reproduccion";
    final static String GETONE = "SELECT nombre FROM listas_de_reproduccion WHERE id=?";

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

    @Override
    public ListsRep obtenerid(Integer id) {
        return null;
    }
}
