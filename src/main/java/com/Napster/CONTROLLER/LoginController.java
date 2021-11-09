package com.Napster.CONTROLLER;

import com.Napster.App;
import com.Napster.MARIADB.Conection;
import com.Napster.Utils.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class LoginController {
    @FXML
    private TextField mail;
    @FXML
    private TextField user;
    @FXML
    private Button login;


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
}
