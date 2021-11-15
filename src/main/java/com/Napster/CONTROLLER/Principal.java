package com.Napster.CONTROLLER;

import com.Napster.MARIADB.MariaDBListRep;
import com.Napster.MARIADB.MariaDBSong;
import com.Napster.MARIADB.MariaDBUser;
import com.Napster.MODEL.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    @FXML
    private ImageView imguser;
    @FXML
    private TableView<ListsRep> listSong;
    @FXML
    private TableView<ListsRep> listarep;
    @FXML
    private TableColumn<ListsRep, String> listado;
    @FXML
    private TableColumn<Song,String> canciones;
    @FXML
    private TableColumn<Artist,String> artista;
    @FXML
    private TableColumn<Album,String> album;
    @FXML
    private TableColumn<Genre,String> genero;



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
    }
    private void tablaCanciones(ListsRep lista){
        List<ArrayList> listadeCanciones = MariaDBListRep.listarCanciones(lista);
        //listSong.setItems(FXCollections.observableArrayList(listadeCanciones));
        canciones.setCellValueFactory(aux -> {
            SimpleStringProperty v = new SimpleStringProperty();
            v.setValue(aux.getValue().getNombre());
            return v;
        });
    }

}
