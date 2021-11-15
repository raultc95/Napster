package com.Napster.CONTROLLER;

import com.Napster.App;
import com.Napster.MARIADB.MariaDBListRep;
import com.Napster.MARIADB.MariaDBSong;
import com.Napster.MARIADB.MariaDBUser;
import com.Napster.MODEL.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    @FXML
    private ImageView imguser;
    @FXML
    private TableView<Song> listSong;
    @FXML
    private TableView<ListsRep> listarep;
    @FXML
    private TableColumn<ListsRep, String> listado;
    @FXML
    private TableColumn<Song,String> canciones;
    @FXML
    private TableColumn<Song,String> artista;
    @FXML
    private TableColumn<Song,String> album;
    @FXML
    private TableColumn<Song,String> genero;
    @FXML
    private MenuItem addList;



    @FXML
    private void initialize() {
        configuraTabla();
        listarep.setItems(FXCollections.observableList(MariaDBListRep.listarPorUsuario(LoginController.usuarioActual)));
        listarep.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> tablaCanciones(newValue));

    }


    private void configuraTabla() {
        listado.setCellValueFactory(lista -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(lista.getValue().getNombre());
            return v;
        });
        canciones.setCellValueFactory(aux -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(aux.getValue().getNombre());
            return v;
        });
        artista.setCellValueFactory(aux ->{
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(aux.getValue().getAlbum().getArtist().getNombre());
            return v;
        });
        album.setCellValueFactory(aux ->{
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(aux.getValue().getAlbum().getNombre());
            return v;
        });
        genero.setCellValueFactory(aux ->{
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(aux.getValue().getGenre().getName());
            return v;
        });
    }

    private void tablaCanciones(ListsRep lista){
        List<Song> listadeCanciones = MariaDBListRep.listarCanciones(lista);
        listSong.setItems(FXCollections.observableList(listadeCanciones));
        //listSong.setItems(FXCollections.observableArrayList(listadeCanciones));
        listSong.refresh();

    }

    @FXML
    private void ventanaCreacion(){
        try {
            App.loadScene(new Stage(), "addPlayList", "Napster", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
