package com.Napster.MARIADB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    private static Connection con = null;
    /*
     *Debe ir cargado desde un xml externo
     */
    private static String uri = "jdbc:mysql://localhost:3307/napster";
    private static String user = "root";
    private static String password = "";

    public static Connection getConexion() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(uri, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
                con = null;
            }

        }
        return con;
    }
    // public void cerrar()
    //   if (con!)
}
