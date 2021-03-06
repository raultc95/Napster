package com.Napster.CONTROLLER;

import com.Napster.App;
import com.Napster.MARIADB.Conection;
import com.Napster.MARIADB.MariaDBUser;
import com.Napster.MODEL.User;
import com.Napster.Utils.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField mail;
    @FXML
    private TextField user;
    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private Button admin;

    MariaDBUser windowUser = new MariaDBUser();
    public static User usuarioActual;

    /**
     * Menu principal, con un registro, inicio de sesion y un inicio de sesion para el administrador,
     * que seria el encargado de añadir y eliminar canciones
     */
    @FXML
    private void initialize() {

    }

    @FXML
    private void cambiar() {
        if (user.getText().equals("admin") && mail.getText().equals("admin")) {
            user.clear();
            try {
                App.loadScene(new Stage(), "administrator", "Administrar", true, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Connection conn = null;
            conn = Conection.getConexion();
            if (conn == null) {
                Dialog.showError("Conexion fallida", "Error de conexion", "No se ha podido conectar con la base de datos");
            } else {
                Dialog.showInformation("Napster", "Gracias por usar nuestro programa", "Bienvenido a Napster");
                try {
                    App.loadScene(new Stage(), "principal", "Napster", true, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @FXML
    private void iniciar() {
        Connection conn = Conection.getConexion();
        if (conn != null) {
            usuarioActual = new User();
            usuarioActual.setNombre(user.getText());
            usuarioActual.setCorreo(mail.getText());
            try {
                if (windowUser.existeUsuario(usuarioActual)) {
                    Dialog.showInformation("LOGIN", "LOGIN realizado con exito", "Gracias por usar nuesrtra aplicacion");
                    App.loadScene(new Stage(), "principal", "Napster", true, true);
                } else {
                    Dialog.showError("ERROR EN EL INICIO", "DATOS INCORRECTOS", "Revise los datos");
                }


            } catch (SQLException | IOException e) {
                e.printStackTrace();
                //Dialog.showError("ERROR EN EL REGISTRO", "El USUARIO y el CORREO ya existen", "Revise los datos");
            }
        }
    }

    @FXML
    private void registrar() {
        Connection conn;
        conn = Conection.getConexion();
        if (conn != null) {
            windowUser.setNombre(user.getText());
            windowUser.setCorreo(mail.getText());
        }
        try {
            windowUser.insertar(new MariaDBUser());
            Dialog.showInformation("REGISTRO", "Registro realizado con exito", "Gracias por usar nuesrtra aplicacion");

        } catch (SQLException e) {
            Dialog.showError("ERROR EN EL REGISTRO", "El USUARIO y el CORREO ya existen", "Revise los datos");
        }

    }
}
