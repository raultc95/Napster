package com.Napster.CONTROLLER;

import com.Napster.DAO.AlbumDAO;
import com.Napster.DAO.GenreDAO;
import com.Napster.MARIADB.*;
import com.Napster.MARIADB.MariaDBAlbum;
import com.Napster.MODEL.Album;
import com.Napster.MODEL.Genre;
import com.Napster.MODEL.Song;
import com.Napster.Utils.Dialog;
import com.Napster.Utils.Utils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddSong {
    @FXML
    private TextField tittle;
    @FXML
    private ComboBox<Album> listaAlbum = new ComboBox<>();
    @FXML
    private TextField duration;
    @FXML
    private ComboBox<Genre> listGenre = new ComboBox<>();
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private ComboBox<Song> idSong = new ComboBox<>();

    private static AddSong.window actual_window = AddSong.window.ADD;

    MariaDBSong song = new MariaDBSong();
    MariaDBAlbum album = new MariaDBAlbum();
    MariaDBGenre genre = new MariaDBGenre();

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
        System.out.println(MariaDBSong.listarTodos());
        System.out.println(MariaDBAlbum.listarTodos());
        System.out.println(MariaDBGenre.listarTodos());
        listaAlbum.setConverter(Utils.albumConverter());
        listGenre.setConverter(Utils.genreConverter());
        listGenre.setItems(FXCollections.observableList(MariaDBGenre.listarTodos()));
        listaAlbum.setItems(FXCollections.observableList(MariaDBAlbum.listarTodos()));

        switch (actual_window.getW()) {
            case "ADD" -> {
                delete.setVisible(false);
                idSong.setVisible(false);
                listGenre.setConverter(Utils.genreConverter());
                listGenre.getItems().setAll(MariaDBGenre.listarTodos());
                listaAlbum.setConverter(Utils.albumConverter());
                listaAlbum.getItems().setAll(MariaDBAlbum.listarTodos());
            }
            case "UPDATE" -> {
                add.setText("ACTUALIZAR");
                idSong.setConverter(Utils.songConverter());
                idSong.getItems().setAll(MariaDBSong.listarTodos());
                idSong.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        tittle.setText(newValue.getNombre());
                        duration.setText(newValue.getDuracion()+"");

                    }
                });
                /*name.setText(actualArtist.getNombre());
                nationality.setText(actualArtist.getNacionalidad());
                url.setText(actualArtist.getFoto());*/
            }
        }

    }

    public static void changeWindow(window w) {
        actual_window = w;
    }

    public void addSong() {
        if (add.getText().equals("AÑADIR")) {
            Connection conn;
            conn = Conection.getConexion();
            if (conn != null) {
                song.setNombre(tittle.getText());
                song.setGenre(listGenre.getSelectionModel().getSelectedItem());
                song.setDuracion(Integer.parseInt(duration.getText()));
                song.setAlbum(listaAlbum.getSelectionModel().getSelectedItem());
            } //listaAlbum.getItems().toString();
            try {
                song.insertar(new MariaDBSong());
                Dialog.showInformation("Cancion Añadida", "La cancion ha sido añadido sin problemas", "Puede continuar");
            } catch (SQLException e) {
                Dialog.showError("ERROR", "ERROR", "ERROR");
            }

        } else if (add.getText().equals("ACTUALIZAR")) {
            updateSong();

        }
    }

    public void updateSong() {
        Connection conn;
        conn = Conection.getConexion();
        if (conn != null) {
            song.setId(idSong.getSelectionModel().getSelectedItem().getId());
            song.setNombre(tittle.getText());
            song.setGenre(listGenre.getSelectionModel().getSelectedItem());
            song.setDuracion(Integer.parseInt(duration.getText()));
            song.setAlbum(listaAlbum.getSelectionModel().getSelectedItem());
            MariaDBSong s = new MariaDBSong();
            s.actualizar(song);
        }
    }
}






