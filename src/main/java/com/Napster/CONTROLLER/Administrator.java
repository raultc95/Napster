package com.Napster.CONTROLLER;

import com.Napster.App;
import com.Napster.MARIADB.MariaDBArtist;
import com.Napster.MARIADB.MariaDBSong;
import com.Napster.MODEL.Artist;
import com.Napster.MODEL.Song;
import com.Napster.CONTROLLER.AddArtist;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class Administrator {
    @FXML
    private Button addArtist;
    @FXML
    private Button addSingle;
    @FXML
    private Button addAlbum;
    @FXML
    private Button addGenre;
    @FXML
    private Button updateArtist;
    @FXML
    private Button updateSingle;
    @FXML
    private Button updateAlbum;
    @FXML
    private Button updateGenre;
    @FXML
    private TableView<MariaDBSong> tableSong;
    @FXML
    private TableColumn<Song, String> listSong;
    @FXML
    private TableColumn<Song, String> listArtist;


    @FXML
    private void initialize() {
        configuraTabla();
        tableSong.setItems(FXCollections.observableList(MariaDBSong.listarTodos()));

    }

    @FXML
    private void addArtist() {
        try {
            AddArtist.changeWindow(AddArtist.window.ADD);
            App.loadScene(new Stage(), "addArtist", "Administrar", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void addSingle() {
        try {
            AddSong.changeWindow(AddSong.window.ADD);
            App.loadScene(new Stage(), "addSong", "Administrar", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void addAlbum() {
        try {
            AddAlbum.changeWindow(AddAlbum.window.ADD);
            App.loadScene(new Stage(), "addAlbum", "Administrar", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addGenre() {
        try {
            AddGenre.changeWindow(AddGenre.window.ADD);
            App.loadScene(new Stage(), "addGenre", "Administrar", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateArtist() {
        try {
            AddArtist.changeWindow(AddArtist.window.UPDATE);
            App.loadScene(new Stage(), "addArtist", "Administrar", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateSingle() {
        try {
            AddSong.changeWindow(AddSong.window.UPDATE);
            App.loadScene(new Stage(), "addSong", "Administrar", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void updateAlbum() {
        try {
            AddAlbum.changeWindow(AddAlbum.window.UPDATE);
            App.loadScene(new Stage(), "addAlbum", "Administrar", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateGenre() {
        try {
            AddGenre.changeWindow(AddGenre.window.UPDATE);
            App.loadScene(new Stage(), "addGenre", "Administrar", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void configuraTabla() {
        listSong.setCellValueFactory(lista -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(lista.getValue().getNombre());
            return v;

        });
        /*
       listArtist.setCellValueFactory(lista -> {
            if(lista!=null){
                SimpleStringProperty v = new SimpleStringProperty();
                v.setValue(lista.getValue().getAlbum().getNombre());
                return v;
            }
            return new SimpleStringProperty("");
       });*/


    }
}
