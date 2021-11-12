package com.Napster.CONTROLLER;

import com.Napster.DAO.ArtistDAO;
import com.Napster.MARIADB.Conection;
import com.Napster.MARIADB.MariaDBAlbum;
import com.Napster.MARIADB.MariaDBArtist;
import com.Napster.Utils.Dialog;
import com.Napster.Utils.Utils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.Napster.MODEL.Artist;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

public class AddArtist {

    @FXML
    private TextField name;
    @FXML
    private TextField nationality;
    @FXML
    private Button photo;
    @FXML
    private TextField url;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private ComboBox<Artist> idArtist = new ComboBox<>();

    private static window actual_window = window.ADD;
    private static MariaDBArtist actualArtist;
    MariaDBArtist artist = new MariaDBArtist();

    public enum window {
        ADD("ADD"),
        UPDATE("UPDATE");
        private final String w;

        window(String w) {
            this.w = w;
        }

        public String getW() {
            return this.w;
        }

    }

    @FXML
    protected void initialize() {
        switch (actual_window.getW()) {
            case "ADD" -> {
                delete.setVisible(false);
                idArtist.setVisible(false);

            }
            case "UPDATE" -> {
                add.setText("ACTUALIZAR");
                idArtist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        name.setText(newValue.getNombre());
                        nationality.setText(newValue.getNacionalidad());
                        url.setText(newValue.getFoto());
                    }
                });
                artist.setNombre(artist.getNombre());
                idArtist.setConverter(Utils.artistConverter());
                idArtist.getItems().setAll(MariaDBArtist.listarTodos());

            }
        }
    }

    @FXML
    public void addArtist() {
        if (add.getText().equals("AÑADIR")) {
            Connection conn;
            conn = Conection.getConexion();
            if (conn != null) {
                artist.setNombre(name.getText());
                artist.setNacionalidad(nationality.getText());
                try {
                    artist.insertar(new MariaDBArtist());

                    Dialog.showInformation("Artista Añadido", "El artista ha sido añadido sin problemas", "Puede continuar");

                } catch (SQLException e) {
                    Dialog.showError("ERROR", "ERROR", "ERROR");
                }
            } else {
                Dialog.showError("Error de Conexion", "No hay conexion a Internet", "Verifique la conexion");
            }
        } else if (add.getText().equals("ACTUALIZAR")) {
            updadteArtist();
        }


    }

    public void updadteArtist() {
        Connection conn;
        conn = Conection.getConexion();
        if (conn != null) {
            artist.setId(idArtist.getSelectionModel().getSelectedItem().getId());
            artist.setNombre(name.getText());
            artist.setNacionalidad(nationality.getText());
            MariaDBArtist a = new MariaDBArtist();
            a.actualizar(artist);

        }


    }

    public void deleteArtist() {
        Connection conn;
        conn = Conection.getConexion();
        if (conn != null) {
            artist.setId(idArtist.getSelectionModel().getSelectedItem().getId());
            try {
                artist.eliminar(artist);
                idArtist.setConverter(Utils.artistConverter());
                idArtist.setItems(FXCollections.observableList(MariaDBArtist.listarTodos()));
                Dialog.showInformation("ARTISTA ELIMINADO", "El artista ha sido eliminado sin problemas", "Puede continuar");

            } catch (SQLException e) {
                Dialog.showError("ERROR", "ERROR", "ERROR");
            }

        }
    }

    public static void changeWindow(window w) {
        actual_window = w;
    }
}

